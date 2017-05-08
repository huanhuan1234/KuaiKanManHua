package xunqaing.bwie.com.kuaikanmanhua.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import xunqaing.bwie.com.kuaikanmanhua.fragment.second.FocusFragment;
import xunqaing.bwie.com.kuaikanmanhua.fragment.second.HotFragment;


public class HomeFragmentAdapter extends FragmentPagerAdapter {

    public HomeFragmentAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        if(position == 0){

            FocusFragment focusFragment = new FocusFragment();
////传值
//            Bundle bundle = new Bundle();
//
//            bundle.putInt("pos",0);
//
//            focusFragment.setArguments(bundle);

            return focusFragment;

        } else {
            HotFragment hotFragment = new HotFragment();

//            Bundle bundle = new Bundle();
//
//            bundle.putInt("pos",0);
//
//            hotFragment.setArguments(bundle);


            return hotFragment;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
