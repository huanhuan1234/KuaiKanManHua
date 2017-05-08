package xunqaing.bwie.com.kuaikanmanhua.fragment.second;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xunqaing.bwie.com.kuaikanmanhua.R;
import xunqaing.bwie.com.kuaikanmanhua.adapter.HotFragmentAdapter;

/**
 *
 * 布局：TabLayout 和 ViewPager
 *
 * 依赖  compile 'com.android.support:design:26.0.0-alpha1'
 *
 * 需要加样式  android:theme="@style/Theme.AppCompat"
 *
 */

public class HotFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.hot_fragment,container,false);

        init(view);

        return view;
    }

    private void init(View view) {

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.hot_fragment_viewpager);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.hot_fragment_tablayout);

        HotFragmentAdapter adapter = new HotFragmentAdapter(getChildFragmentManager());

        viewPager.setAdapter(adapter);

        //TabLayout 和 ViewPager 关联
        tabLayout.setupWithViewPager(viewPager);

        // 设置 选中 未选中 字的颜色
        tabLayout.setTabTextColors(getResources().getColor(R.color.cgray),getResources().getColor(R.color.coffer));

        // 设置 指示器的颜色
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.title_bg));

        //设置滚动模式
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

//        设置正常模式
//        tabLayout.setTabMode(TabLayout.MODE_FIXED);

    }


}
