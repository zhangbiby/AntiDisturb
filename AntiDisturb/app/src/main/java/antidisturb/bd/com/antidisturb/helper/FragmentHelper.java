package antidisturb.bd.com.antidisturb.helper;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import antidisturb.bd.com.antidisturb.R;
import antidisturb.bd.com.antidisturb.bean.PhoneNum;
import antidisturb.bd.com.antidisturb.fagment.SmsFragment;
import antidisturb.bd.com.antidisturb.listener.PhoneNumAsyncListener;

/**
 * Created by Administrator on 2017/3/11.
 */
public class FragmentHelper implements PhoneNumAsyncListener {

    public static final int FRAGMENT_SMS = 1;
    private Fragment fragment;
    private int theFragmentType;// the data input
    private PhoneNumHelper phoneNumHelper;
    private InterceptInfoHelper interceptInfoHelper;
    private Button textLeft;
    private TextView textCenter;
    private Button textRight;
    private LinearLayout bottomLayout;
    private TextView operateText;
    private ListView listView;

    public FragmentHelper(SmsFragment smsFragment, int fragmentSms, View view) {
        this.fragment = smsFragment;
        theFragmentType = fragmentSms;
        init(view);
    }

    private void init(View view) {
        phoneNumHelper = new PhoneNumHelper(fragment.getActivity(),this);
        interceptInfoHelper = new InterceptInfoHelper(fragment.getActivity(),this);
        textLeft = (Button) view.findViewById(R.id.textLeft);
        textCenter = (TextView) view
                .findViewById(R.id.textCenter);
        textRight = (Button) view.findViewById(R.id.textRight);
        bottomLayout = (LinearLayout) view.findViewById(
                R.id.bottomLayout);
        operateText = (TextView) view.findViewById(
                R.id.operateText);
        listView = (ListView) view.findViewById(R.id.listView);

        switch (theFragmentType){
           case  FRAGMENT_SMS:// sms
            textCenter.setText(getStringValue(R.string.intercept_sms));
            bottomLayout.setVisibility(View.GONE);
           // interceptInfoHelper.querySms(noPage);
            break;
        }
    }

    private String getStringValue(int stringId) {
        return fragment.getString(stringId);
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
}
