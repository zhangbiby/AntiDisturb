package antidisturb.bd.com.antidisturb;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import antidisturb.bd.com.antidisturb.fagment.BlackFragment;
import antidisturb.bd.com.antidisturb.fagment.CallFragment;
import antidisturb.bd.com.antidisturb.fagment.SmsFragment;
import antidisturb.bd.com.antidisturb.fagment.WhiteFragment;
import antidisturb.bd.com.antidisturb.helper.ViewPagerHelper;

public class MainActivity extends FragmentActivity {

    private ViewPager viewPager ;
    private List<Fragment> listFragment;
    private Fragment smsFragment,callFragment,blackFragment,whiteFragment;
    private ViewPagerHelper viewPagerHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
    }

    private void initFragments() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        listFragment = new ArrayList<Fragment>();
        smsFragment = new SmsFragment();
        callFragment = new CallFragment();
        blackFragment = new BlackFragment();
        whiteFragment = new WhiteFragment();

        listFragment.add(smsFragment);
        listFragment.add(callFragment);
        listFragment.add(blackFragment);
        listFragment.add(whiteFragment);

        viewPagerHelper = new ViewPagerHelper(viewPager,getSupportFragmentManager(),listFragment);
        viewPagerHelper.init();
    }
}
