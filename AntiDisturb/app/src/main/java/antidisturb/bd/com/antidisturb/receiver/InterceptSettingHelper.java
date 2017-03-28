package antidisturb.bd.com.antidisturb.receiver;

import android.content.Context;

import antidisturb.bd.com.antidisturb.bean.PhoneNum;

/**
 * Created by Administrator on 2017/3/28.
 */
public class InterceptSettingHelper {
    public InterceptSettingHelper(Context context) {
    }

    public boolean isSmsInterceptNow(String number, PhoneNum phoneNum, String contactsName) {
        //default intercept all message
        return true;
    }
}
