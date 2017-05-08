package xunqaing.bwie.com.kuaikanmanhua.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import xunqaing.bwie.com.kuaikanmanhua.fragment.third.WeekFragment;

public class HotFragmentAdapter extends FragmentPagerAdapter {

    String [] TITLE = {"周一","周二","周三","周四","周五","周六","周日"} ;

    public HotFragmentAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }




    //返回Fragment  传标题的值
    @Override
    public Fragment getItem(int position) {

        WeekFragment weekFragment = new WeekFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title",TITLE[position]);
        weekFragment.setArguments(bundle);

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
