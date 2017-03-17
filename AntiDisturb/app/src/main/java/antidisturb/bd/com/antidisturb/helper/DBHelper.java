package antidisturb.bd.com.antidisturb.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/3/13.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    public static final String DB_NAME = "markphone.db";

    public static final String TABLE_1_NAME = "marklist";
    public static final String TABLE_1_COLUMN_PHONENUM = "phonenum";
    public static final String TABLE_1_COLUMN_USERNAME = "username";
    public static final String TABLE_1_COLUMN_REMARK = "remark";
    public static final String TABLE_1_COLUMN_FLAG = "flag";

    public static final String TABLE_2_NAME = "interceptlist";
    public static final String TABLE_2_COLUMN_PHONENUM = "phonenum";
    public static final String TABLE_2_COLUMN_NAME = "name";
    public static final String TABLE_2_COLUMN_INFO = "info";
    public static final String TABLE_2_COLUMN_TIME = "time";
    public static final String TABLE_2_COLUMN_TYPE = "type";
    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_1_NAME+ "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +TABLE_1_COLUMN_PHONENUM+" VARCHAR, "
                +TABLE_1_COLUMN_USERNAME+" VARCHAR, "+TABLE_1_COLUMN_REMARK+" VARCHAR, "+TABLE_1_COLUMN_FLAG+" INTEGER); ");

        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_2_NAME+ "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +TABLE_2_COLUMN_PHONENUM+" VARCHAR, "+TABLE_2_COLUMN_NAME+" VARCHAR, "+TABLE_2_COLUMN_INFO+" VARCHAR, "+TABLE_2_COLUMN_TIME+" LONG, "
                + TABLE_2_COLUMN_TYPE+" VARCHAR) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        DB_VERSION = newVersion;
        db.execSQL("ALTER TABLE marklist ADD COLUMN other STRING");
    }
}
