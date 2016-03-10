package net.hcii.customview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

import net.hcii.customview.view.TabView;
import net.hcii.customview.R;


public class MainActivity extends ActionBarActivity {
    private String[] mTitle = {"wechat","contact","find","me"};
    private int[] mIconSelect = {R.drawable.al_, R.drawable.al8, R.drawable.alb, R.drawable.ald};
    private int[] mIconNormal = {R.drawable.ala, R.drawable.al9, R.drawable.alc, R.drawable.ale};
    private ViewPager mViewPager ;
    private TabView mTabView ;
    private Map<Integer,Fragment> mFragmentMap ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentMap = new HashMap<>() ;
        mViewPager = (ViewPager)findViewById(R.id.id_view_pager) ;
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        mTabView = (TabView)findViewById(R.id.id_tab) ;
        mTabView.setViewPager(mViewPager);
    }

    private Fragment getFragment(int position){
        Fragment fragment = mFragmentMap.get(position) ;
        if(fragment == null){
            switch (position){
                case 0:
                    fragment = new WeChatFragment() ;
                    break ;
                case 1:
                    fragment = new WeContactFragment();
                    break ;
                case 2:
                    fragment = new WeDiscoverFragment();
                    break;
                case 3:
                    fragment = new GameFragment() ;
//                    fragment = new WeMineFragment();
                    break;
            }
            mFragmentMap.put(position,fragment) ;
        }
        return fragment ;
    }

    class PageAdapter extends FragmentPagerAdapter implements TabView.OnItemIconTextSelectListener{

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return getFragment(position);
        }
        @Override
        public int[] onIconSelect(int position) {
            int icon[] = new int[2] ;
            icon[0] = mIconSelect[position] ;
            icon[1] = mIconNormal[position] ;
            return icon;
        }
        @Override
        public String onTextSelect(int position) {
            return mTitle[position];
        }

        @Override
        public int getCount() {
            return mTitle.length;
        }
    }
}
