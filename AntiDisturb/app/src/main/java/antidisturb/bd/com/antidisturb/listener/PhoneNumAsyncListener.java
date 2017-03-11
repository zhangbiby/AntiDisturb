package antidisturb.bd.com.antidisturb.listener;

import java.util.List;

import antidisturb.bd.com.antidisturb.bean.PhoneNum;

/**
 * Created by Administrator on 2017/3/11.
 */
public interface PhoneNumAsyncListener {
    public abstract void onQueryBlackListComplete(List<PhoneNum> phoneNumList);
    public abstract void onQueryWhiteListComplete(List<PhoneNum> phoneNumList);
    public abstract void onQueryPhoneNumComplete(Object cookie,PhoneNum phoneNum);
    public abstract void onAsyncInsertPhoneNumComplete(List<PhoneNum> allList,List<PhoneNum> failedList);

    public abstract void onAsyncUpdatePhoneNumComplete(List<PhoneNum> failedUpdateList);
}
