package antidisturb.bd.com.antidisturb.listener;

import java.util.List;

import antidisturb.bd.com.antidisturb.bean.InterceptInfo;

/**
 * Created by Administrator on 2017/3/13.
 */
public interface InterceptInfoAsyncListener {
    public void onQuerySmsComplete(List<InterceptInfo> resultList);
    public void onQueryCallComplete(List<InterceptInfo> resultList);
}
