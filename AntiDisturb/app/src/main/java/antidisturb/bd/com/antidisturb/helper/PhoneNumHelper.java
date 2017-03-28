package antidisturb.bd.com.antidisturb.helper;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.PhoneNumberUtils;


import java.util.ArrayList;
import java.util.List;

import antidisturb.bd.com.antidisturb.bean.PhoneNum;
import antidisturb.bd.com.antidisturb.listener.PhoneNumAsyncListener;
import antidisturb.bd.com.antidisturb.receiver.InterceptReceiver;
import antidisturb.bd.com.antidisturb.util.Contact;
import antidisturb.bd.com.antidisturb.util.MyProgressDialog;

/**
 * Created by Administrator on 2017/3/11.
 */

public class PhoneNumHelper extends AsyncQueryHandler {

    private Context mContext;
    private PhoneNumAsyncListener listener;
    private Cursor cursor;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private MyProgressDialog progressDialog;
    private ContentResolver contentResolver;
    private List<PhoneNum> resultList;
    public PhoneNumHelper(Context context,PhoneNumAsyncListener listener){
        super(context.getContentResolver());
        mContext = context;
        this.listener = listener;
        dbHelper = new DBHelper(context);
        progressDialog = new MyProgressDialog(context);
        contentResolver = context.getContentResolver();

        if(db == null || !db.isOpen()){
            db = dbHelper.getWritableDatabase();
        }
        resultList = new ArrayList<PhoneNum>();
    }
    public PhoneNum queryDBWithCompare(String phoneNum) {
        PhoneNum result = null;
        String dbNum = "",userName ="",remark = "";
        int flag = 0;
        cursor = db.rawQuery("SELECT * FROM "+DBHelper.TABLE_1_NAME,null);
        if(cursor.moveToFirst()){
            dbNum = cursor.getString(cursor.getColumnIndex(DBHelper.TABLE_1_COLUMN_PHONENUM));
            if(PhoneNumberUtils.compare(phoneNum,dbNum)){
                flag = cursor.getInt(cursor.getColumnIndex(DBHelper.TABLE_1_COLUMN_FLAG));
                userName = Contact.getContactInfoForPhoneNumber(mContext, dbNum);//cursor.getString(cursor.getColumnIndex(DBHelper.TABLE_1_COLUMN_USERNAME));
                remark  = cursor.getString(cursor.getColumnIndex(DBHelper.TABLE_1_COLUMN_REMARK));
                result = new PhoneNum(dbNum,userName,remark,flag);
                return result;
            }
        }while (cursor.moveToNext());

        cursor.close();
//		progressDialog.dismiss();
        return result;
    }
}
