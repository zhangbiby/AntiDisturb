package antidisturb.bd.com.antidisturb.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import antidisturb.bd.com.antidisturb.helper.DBHelper;

/**
 * Created by Administrator on 2017/3/13.
 */
public class InterceptInfoProvider extends ContentProvider {

    public static final String TABLE_NAME = DBHelper.TABLE_2_NAME;
    public static final String COLUMN_PHONENUM = DBHelper.TABLE_2_COLUMN_PHONENUM;
    public static final String COLUMN_TYPE = DBHelper.TABLE_2_COLUMN_TYPE;
    public static final String COLUMN_TIME = DBHelper.TABLE_2_COLUMN_TIME;
    public static final String COLUMN_INFO = DBHelper.TABLE_2_COLUMN_INFO;
    private static final String AUTHORITY = "antidisturb.bd.com.antidisturb.provider.InterceptInfoProvider";
    public static final Uri NOTIFY_URI = Uri.parse("content://" + AUTHORITY + "/interceptlists");
    private DBHelper helper;
    private SQLiteDatabase db;
    private static  final UriMatcher matcher ;
    private static final int INTERCEPT_ALL = 0;
    private static final int INTERCEPT_ONE = 1;
    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(AUTHORITY,"interceptlists",INTERCEPT_ALL);
        matcher.addURI(AUTHORITY, "interceptlists/#", INTERCEPT_ONE);
    }
        @Override
    public boolean onCreate() {
        helper = new DBHelper(getContext());
        db = helper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);

    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }


}
