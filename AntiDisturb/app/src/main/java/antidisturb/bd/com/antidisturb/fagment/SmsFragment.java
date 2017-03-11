package antidisturb.bd.com.antidisturb.fagment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import antidisturb.bd.com.antidisturb.R;
import antidisturb.bd.com.antidisturb.helper.FragmentHelper;

/**
 * Created by Administrator on 2017/3/10.
 */
public class SmsFragment extends android.support.v4.app.Fragment {

    private FragmentHelper fragmentHelper;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        fragmentHelper = new FragmentHelper(this,FragmentHelper.FRAGMENT_SMS,view);
        return view;
    }
}
