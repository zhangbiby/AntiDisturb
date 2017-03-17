package antidisturb.bd.com.antidisturb.provider;



import android.database.ContentObserver;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Administrator on 2017/3/13.
 */
public class DataObserver  extends ContentObserver{

    public static final int DATA_PHONENUM_CHANGED  = 1;
    public static final int DATA_INTERCEPT_CHANGED = 2;
    public static final int DATA_CONTACT_CHANGE = 3;
    private Handler handler;
    private int dataType;
    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public DataObserver(Handler handler,int type) {
        super(handler);
        this.handler = handler;
        this.dataType = type;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Message msg = new Message();
        switch(dataType){
            case DATA_INTERCEPT_CHANGED:
                msg.what = DATA_INTERCEPT_CHANGED;break;
            case DATA_PHONENUM_CHANGED:
                msg.what = DATA_PHONENUM_CHANGED;break;
            case DATA_CONTACT_CHANGE:
                msg.what = DATA_CONTACT_CHANGE;break;
        }
        handler.sendMessage(msg);
    }
}
