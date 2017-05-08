package xunqaing.bwie.com.kuaikanmanhua.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import xunqaing.bwie.com.kuaikanmanhua.fragment.third.WeekFragment;
public class CommunityAdapter extends FragmentPagerAdapter {

     String []TITLE={"热门","最新"};

    public CommunityAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {
        WeekFragment weekFragment= new WeekFragment();
        return weekFragment;


    }

    @Override
    public int getCount() {
        return TITLE.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLE[position];
    }



}
