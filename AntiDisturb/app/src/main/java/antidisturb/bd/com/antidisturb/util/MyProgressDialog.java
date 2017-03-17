package antidisturb.bd.com.antidisturb.util;

import android.app.ProgressDialog;
import android.content.Context;

import antidisturb.bd.com.antidisturb.R;

/**
 * Created by Administrator on 2017/3/13.
 */
public class MyProgressDialog extends ProgressDialog {
    public MyProgressDialog(Context context) {
        super(context);
        this.setCancelable(false);
        this.setMessage(context.getString(R.string.dataOprating));
    }
}
