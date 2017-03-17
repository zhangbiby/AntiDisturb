package antidisturb.bd.com.antidisturb.helper;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

import antidisturb.bd.com.antidisturb.bean.InterceptInfo;
import antidisturb.bd.com.antidisturb.listener.InterceptInfoAsyncListener;
import antidisturb.bd.com.antidisturb.provider.InterceptInfoProvider;
import antidisturb.bd.com.antidisturb.util.Constant;
import antidisturb.bd.com.antidisturb.util.Contact;
import antidisturb.bd.com.antidisturb.util.MyProgressDialog;

/**
 * Created by Administrator on 2017/3/11.
 */
public class InterceptInfoHelper extends AsyncQueryHandler {

    private InterceptInfoAsyncListener listener;
    private Context mContext;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private ContentResolver contentResolver;
    private MyProgressDialog progressDialog;
    private List<InterceptInfo> resultList;

    private String selection;
    private String[] selectionArgs;
    private static final int pageSize = 30;
    private static final int QUERY_SMS = 1;
    private static final Uri CONTENT_URI = InterceptInfoProvider.NOTIFY_URI;
    public InterceptInfoHelper(Context context, InterceptInfoAsyncListener listener) {

        super(context.getContentResolver());
        this.listener = listener;
        this.mContext = context;
        dbHelper = new DBHelper(context);
        contentResolver = context.getContentResolver();
        progressDialog = new MyProgressDialog(context);
        if(db == null || !db.isOpen()){
            db = dbHelper.getWritableDatabase();
        }
        resultList = new ArrayList<InterceptInfo>();
    }

    public void querySms(int pageNum) {
        progressDialog.show();
        selection = InterceptInfoProvider.COLUMN_TYPE+" = ?";
        selectionArgs = new String[]{String.valueOf(InterceptInfo.TYPE_SMS)};
        if(pageNum != FragmentHelper.noPage)
            selection+= " LIMIT "+String.valueOf(pageSize)+ " OFFSET " +String.valueOf(pageNum*pageSize);

        startQuery(QUERY_SMS,null, CONTENT_URI, null, selection, selectionArgs, InterceptInfoProvider.COLUMN_TIME+" desc");
    }

    @Override
    protected void onQueryComplete(int token, Object cookie, Cursor cursor) {
        resultList.clear();
        switch (token){
            case QUERY_SMS:
                try{
                    while(cursor.moveToNext()){
                        String address = cursor.getString(cursor.getColumnIndex(InterceptInfoProvider.COLUMN_PHONENUM));
                        address = Constant.convertPhoneNum(address);
                        String smsbody = cursor.getString(cursor.getColumnIndex(InterceptInfoProvider.COLUMN_INFO));
                        Long time = cursor.getLong(cursor.getColumnIndex(InterceptInfoProvider.COLUMN_TIME));
                        String name = Contact.getContactInfoForPhoneNumber(mContext, address);//cursor.getString(cursor.getColumnIndex(InterceptInfoProvider.COLUMN_NAME));

                        resultList.add(new InterceptInfo(address,name,smsbody,time,InterceptInfo.TYPE_SMS));
                    }
                }catch(Exception e){

                }
                listener.onQuerySmsComplete(resultList);
                break;
        }
        if (cursor != null && !cursor.isClosed())
            cursor.close();
        if(progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
