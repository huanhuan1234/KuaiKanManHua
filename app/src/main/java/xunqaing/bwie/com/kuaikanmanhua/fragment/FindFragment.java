package xunqaing.bwie.com.kuaikanmanhua.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xunqaing.bwie.com.kuaikanmanhua.R;
import xunqaing.bwie.com.kuaikanmanhua.adapter.CommunityAdapter;


public class FindFragment extends Fragment {  // 社区

    private View view;
    private ViewPager vpvcomm;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.community_fragment, container, false);
        toFindview();
        initTab();
        vpvcomm.setAdapter(new CommunityAdapter(getActivity().getSupportFragmentManager()));


        return view;
    }

    private void initTab() {
        tabLayout.setupWithViewPager(vpvcomm);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_login));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabTextColors(getResources().getColor(R.color.gray),getResources().getColor(R.color.tab_login));
    }

    private void toFindview() {
        vpvcomm = (ViewPager) view.findViewById(R.id.viewpager_vcomm);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout_vcomm);

    }

}
