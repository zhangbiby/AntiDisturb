package antidisturb.bd.com.antidisturb.helper;

import android.content.ContentResolver;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import antidisturb.bd.com.antidisturb.R;
import antidisturb.bd.com.antidisturb.bean.InterceptInfo;
import antidisturb.bd.com.antidisturb.bean.PhoneNum;
import antidisturb.bd.com.antidisturb.fagment.SmsFragment;
import antidisturb.bd.com.antidisturb.listener.InterceptInfoAsyncListener;
import antidisturb.bd.com.antidisturb.listener.ListViewStateChangeListener;
import antidisturb.bd.com.antidisturb.listener.PhoneNumAsyncListener;
import antidisturb.bd.com.antidisturb.provider.DataObserver;

/**
 * Created by Administrator on 2017/3/11.
 */
public class FragmentHelper implements ListViewStateChangeListener, PhoneNumAsyncListener,InterceptInfoAsyncListener {

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
    public static final int noPage = -1;

    private MyClickListener listener;
    private ContentResolver mContentResolver;
    public DataObserver mPhoneNumberChageObserver;
    public DataObserver mInterceptChangeObserver;
    public DataObserver mContactChangeObserver;
    private ListViewHelper listViewHelper;
    private List<InterceptInfo> interceptList = new ArrayList<InterceptInfo>();
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
            interceptInfoHelper.querySms(noPage);
            break;
        }

        listener = new MyClickListener();
        textLeft.setOnClickListener(listener);
        textCenter.setOnClickListener(listener);
        textRight.setOnClickListener(listener);
        bottomLayout.setOnClickListener(listener);
        mContentResolver = fragment.getActivity().getContentResolver();
        mPhoneNumberChageObserver = new DataObserver(handler,DataObserver.DATA_PHONENUM_CHANGED);
        mInterceptChangeObserver = new DataObserver(handler,DataObserver.DATA_INTERCEPT_CHANGED);
        mContactChangeObserver = new DataObserver(handler,DataObserver.DATA_CONTACT_CHANGE);

        initListView(interceptList);
    }

    private void initListView(List<InterceptInfo> interceptList){
        listViewHelper = new ListViewHelper(listView,theFragmentType,ListViewHelper.LISTVIEW_FRAGMENT, interceptList);
        listViewHelper.setCheckStateChangeListener(this);
    }
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            //update records.
            onDataChange(msg.what);
        };
    };

    private void onDataChange(int what) {
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

    @Override
    public void onQuerySmsComplete(List<InterceptInfo> resultList) {
        if(resultList != null){
            interceptList.clear();
            interceptList = resultList;
            listViewHelper.setDataToShow(interceptList);
        }
    }

    @Override
    public void onQueryCallComplete(List<InterceptInfo> resultList) {

    }

    @Override
    public void OnListViewCheckStateChange(boolean isCheck) {

    }

    @Override
    public void OnListViewCheckNumChange(int checkedNum) {

    }

    @Override
    public void OnListViewBlackDataDeleted(List<PhoneNum> phoneNumList) {

    }

    @Override
    public void OnListViewWhiteDataDeleted(List<PhoneNum> phoneNumList) {

    }

    @Override
    public void OnListViewInterceptSmsDeleted(List<InterceptInfo> interceptList) {

    }

    @Override
    public void OnListViewInterceptCallDeleted(List<InterceptInfo> interceptList) {

    }

    @Override
    public void OnListViewQuerySmsToAppend() {

    }

    @Override
    public void OnListViewQueryCallToAppend() {

    }

    class MyClickListener implements View.OnClickListener {


         @Override
         public void onClick(View v) {
             switch (v.getId()){

             }
         }
     }
}
