package antidisturb.bd.com.antidisturb.helper;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;
import android.widget.Scroller;

import java.lang.reflect.Field;
import java.util.List;

import antidisturb.bd.com.antidisturb.R;

/**
 * Created by Administrator on 2017/3/10.
 */
public class ViewPagerHelper {

    public static final int CURRENTFRAGMENT_SMS = 0;
    public static final int CURRENTFRAGMENT_CALL = 1;
    public static final int CURRENTFRAGMENT_BLACK = 2;
    public static final int CURRENTFRAGMENT_WHITE = 3;

    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private Context context;
    private RadioGroup radioGroup;
    public ViewPagerHelper(ViewPager viewPager, FragmentManager supportFragmentManager, List<Fragment> listFragment) {

        this.viewPager = viewPager;
        this.fragmentList = listFragment;
        viewPager.setAdapter(new MyViewPagerAdapter(supportFragmentManager,fragmentList));
        viewPager.setOffscreenPageLimit(4);
        context = viewPager.getContext();
    }
    public void init(){
        radioGroup = (RadioGroup) ((Activity) context)
                .findViewById(R.id.radioGroup);
        radioGroup.check(R.id.sms_antidisturb);
        radioGroup.setOnCheckedChangeListener(new MyRadioGroupListener());
        ViewPagerScroller scroller = new ViewPagerScroller(context);
        scroller.initViewPagerScroll(viewPager);

        viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }


    private class MyViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList;
        public MyViewPagerAdapter(FragmentManager fm,
                                  List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }

    private class ViewPagerScroller extends Scroller{

        private int mScrollDuration = 500;//the speed
        public ViewPagerScroller(Context context) {
            super(context);
        }
        public void setScrollDuration(int duration){
            this.mScrollDuration = duration;
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy,mScrollDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mScrollDuration);
        }

        public void initViewPagerScroll(ViewPager viewPager) {

            try {
                Field mScroller = ViewPager.class.getDeclaredField("mScroller");
                mScroller.setAccessible(true);
                mScroller.set(viewPager, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch(position){
                case CURRENTFRAGMENT_SMS:radioGroup.check(R.id.sms_antidisturb);break;
                case CURRENTFRAGMENT_CALL:radioGroup.check(R.id.call_antidisturb);break;
                case CURRENTFRAGMENT_BLACK:radioGroup.check(R.id.blacknum_antidisturb);break;
                case CURRENTFRAGMENT_WHITE:radioGroup.check(R.id.whitenum_antidisturb);break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class MyRadioGroupListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.sms_antidisturb:viewPager.setCurrentItem(CURRENTFRAGMENT_SMS);
                    break;
                case R.id.call_antidisturb:viewPager.setCurrentItem(CURRENTFRAGMENT_CALL);
                    break;
                case R.id.blacknum_antidisturb:viewPager.setCurrentItem(CURRENTFRAGMENT_BLACK);
                    break;
                case R.id.whitenum_antidisturb:viewPager.setCurrentItem(CURRENTFRAGMENT_WHITE);
                    break;
            }
        }
    }
}
