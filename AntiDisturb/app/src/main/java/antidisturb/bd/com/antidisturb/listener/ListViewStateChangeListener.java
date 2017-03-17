package antidisturb.bd.com.antidisturb.listener;

import java.util.List;

import antidisturb.bd.com.antidisturb.bean.InterceptInfo;
import antidisturb.bd.com.antidisturb.bean.PhoneNum;

/**
 * Created by Administrator on 2017/3/13.
 */
public interface ListViewStateChangeListener {
    public abstract void OnListViewCheckStateChange(boolean isCheck);
    public abstract void OnListViewCheckNumChange(int checkedNum);
    public abstract void OnListViewBlackDataDeleted(List<PhoneNum> phoneNumList);
    public abstract void OnListViewWhiteDataDeleted(List<PhoneNum> phoneNumList);
    public abstract void OnListViewInterceptSmsDeleted(List<InterceptInfo> interceptList);
    public abstract void OnListViewInterceptCallDeleted(List<InterceptInfo> interceptList);
    public abstract void OnListViewQuerySmsToAppend();
    public abstract void OnListViewQueryCallToAppend();
}
