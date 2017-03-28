package antidisturb.bd.com.antidisturb.receiver;

import android.content.BroadcastReceiver;
import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.util.List;

import antidisturb.bd.com.antidisturb.bean.InterceptInfo;
import antidisturb.bd.com.antidisturb.bean.PhoneNum;
import antidisturb.bd.com.antidisturb.helper.InterceptInfoHelper;
import antidisturb.bd.com.antidisturb.helper.PhoneNumHelper;
import antidisturb.bd.com.antidisturb.listener.InterceptInfoAsyncListener;
import antidisturb.bd.com.antidisturb.listener.PhoneNumAsyncListener;
import antidisturb.bd.com.antidisturb.util.Contact;

/**
 * Created by Administrator on 2017/3/28.
 */
public class InterceptReceiver extends BroadcastReceiver implements PhoneNumAsyncListener,InterceptInfoAsyncListener {

    public static final String INTERCEPT_SMS = "android.provider.Telephony.BIRD_SMS_RECEIVED";

    private int theInfoType = InterceptInfo.TYPE_ERROR;

    private String content = "";
    private long time = 0;
    private PhoneNumHelper phoneNumHelper;
    private Context context;
    private InterceptSettingHelper settingHelper;
    private InterceptInfoHelper interceptInfoHelper;
    @Override
    public void onReceive(Context context, Intent intent) {

        init(context);

        if(INTERCEPT_SMS.equals(intent.getAction()))
              {
                  interceptSms(intent);
              }
    }

    private void init(Context context) {
        this.context = context;
        phoneNumHelper = new PhoneNumHelper(context, this);
        interceptInfoHelper = new InterceptInfoHelper(context, this);
        settingHelper = new InterceptSettingHelper(context);
    }

    private void interceptSms(Intent intent) {
        theInfoType = InterceptInfo.TYPE_SMS;
        Bundle bundle = intent.getExtras();
        SmsMessage msg = null;
        if(bundle!=null){
            Object[] pdusObj = (Object[]) bundle.get("pdus");
            for (Object p : pdusObj){
                msg = SmsMessage.createFromPdu((byte[]) p);
                content = msg.getMessageBody();// sms content
                time = msg.getTimestampMillis();// time
                String number = msg.getOriginatingAddress();
                operateSms(number);
            }
        }
    }

    private void operateSms(final String number) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                PhoneNum phoneNum = phoneNumHelper.queryDBWithCompare(number);
                String contactsName =  Contact.getContactInfoForPhoneNumber(context, number);
                if (settingHelper.isSmsInterceptNow(number, phoneNum,
                        contactsName)) {
                    String name = phoneNum == null ? "" : phoneNum
                            .getUserName();
                    if ("".equals(name) && contactsName != null
                            && !"".equals(contactsName))
                        name = contactsName;

                    interceptInfoHelper.insertDB(new InterceptInfo(number,
                            name, content, time, InterceptInfo.TYPE_SMS));
                }

            }
        }).start();
    }

    @Override
    public void onQueryBlackListComplete(List<PhoneNum> phoneNumList) {

    }

    @Override
    public void onQueryWhiteListComplete(List<PhoneNum> phoneNumList) {

    }

    @Override
    public void onQueryPhoneNumComplete(Object cookie, PhoneNum phoneNum) {

    }

    @Override
    public void onAsyncInsertPhoneNumComplete(List<PhoneNum> allList, List<PhoneNum> failedList) {

    }

    @Override
    public void onAsyncUpdatePhoneNumComplete(List<PhoneNum> failedUpdateList) {

    }

    @Override
    public void onQuerySmsComplete(List<InterceptInfo> resultList) {

    }

    @Override
    public void onQueryCallComplete(List<InterceptInfo> resultList) {

    }
}
