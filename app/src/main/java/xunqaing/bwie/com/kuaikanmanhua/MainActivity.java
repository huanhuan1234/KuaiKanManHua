package xunqaing.bwie.com.kuaikanmanhua;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import xunqaing.bwie.com.kuaikanmanhua.base.BaseActivity;
import xunqaing.bwie.com.kuaikanmanhua.fragment.FeedFragment;
import xunqaing.bwie.com.kuaikanmanhua.fragment.FindFragment;
import xunqaing.bwie.com.kuaikanmanhua.fragment.HomeFragment;
import xunqaing.bwie.com.kuaikanmanhua.fragment.MeFragment;
import xunqaing.bwie.com.kuaikanmanhua.listener.ViewListener;

//实现找控件的接口, 继承 一个自己创建的Activity 执行并行方法
public class MainActivity extends BaseActivity implements ViewListener {

    private RadioGroup rg;
    private RadioButton rb_Home;
    private RadioButton rb_find;
    private RadioButton rb_feed;
    private RadioButton rb_me;

    //创建集合 存入Fragment
    private List<Fragment> listfragment = new ArrayList<>();

    //定义下标
    private int selectIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //调用接口方法
        init();
        //创建Fragment
        createFragment(savedInstanceState);

        //显示Fragment
        showFragment(0);
    }

    //将Fragment加进FrameLayout 根据下标判断从集合中加哪个Fragment进去
    private void showFragment(int position) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();


        //展示和隐藏
        //如果刚开始没有Fragment加进了fragmentManager
        if (!listfragment.get(position).isAdded()) {

//            transaction.add(R.id.container, listfragment.get(position));
            //防止Frgament错乱 --- 获取每个Fragment的值 作为标签
            transaction.add(R.id.container, listfragment.get(position),
                    listfragment.get(position).getClass().getSimpleName());

        }

        for (int i = 0; i < listfragment.size(); i++) {

            if (position == i) {

                transaction.show(listfragment.get(i));

            } else {

                transaction.hide(listfragment.get(i));
            }

        }

        transaction.commit();

    }

    //临时取值
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        System.out.println(savedInstanceState.getString("a"));
    }


    //临时存值主要用于在Activity销毁时保存一些信息。
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putString("a",outPersistentState+"");

    }

    //创建Fragment
    private void createFragment(Bundle savedInstanceState) {

//        HomeFragment homeFragment = new HomeFragment();
//        FeedFragment feedFragment = new FeedFragment();
//        MeFragment meFragment = new MeFragment();
//        FindFragment findFragment = new FindFragment();
//
//        listfragment.add(homeFragment);
//        listfragment.add(feedFragment);
//        listfragment.add(meFragment);
//        listfragment.add(findFragment);

                HomeFragment homeFragment = null;
                FeedFragment feedFragment = null;
                FindFragment findFragment = null;
                MeFragment meFragment = null;

        if (savedInstanceState == null) {

            homeFragment = new HomeFragment();
            feedFragment = new FeedFragment();
            findFragment = new FindFragment();
            meFragment = new MeFragment();

        }else{

            homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag("homeFragment");
            feedFragment = (FeedFragment) getSupportFragmentManager().findFragmentByTag("feedFragment");
            findFragment = (FindFragment) getSupportFragmentManager().findFragmentByTag("findFragment");
            meFragment = (MeFragment) getSupportFragmentManager().findFragmentByTag("meFragment");


            if (homeFragment == null){

                homeFragment = new HomeFragment();
            }

            if (feedFragment == null){

                feedFragment = new FeedFragment();
            }


            if (findFragment == null){

                findFragment = new FindFragment();
            }

            if (meFragment == null){

                meFragment = new MeFragment();
            }
        }

                listfragment.add(homeFragment);
                listfragment.add(feedFragment);
                listfragment.add(findFragment);
                listfragment.add(meFragment);


    }


    //实现找控件接口方法
    @Override
    public void init() {

        rg = (RadioGroup) findViewById(R.id.tab_radiogroup);
        rb_Home = (RadioButton) findViewById(R.id.tab_radiobutton_home);
        rb_find = (RadioButton) findViewById(R.id.tab_radiobutton_find);
        rb_feed = (RadioButton) findViewById(R.id.tab_radiobutton_feed);
        rb_me = (RadioButton) findViewById(R.id.tab_radiobutton_me);

        //RadioGroup监听事件
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {

                    case R.id.tab_radiobutton_home:

                        selectIndex =0;

                        break;

                    case R.id.tab_radiobutton_find:

                        selectIndex =1;

                        break;

                    case R.id.tab_radiobutton_feed:

                        selectIndex =2;

                        break;

                    case R.id.tab_radiobutton_me:

                        selectIndex =3;

                        break;
                }

                showFragment(selectIndex);

            }
        });

    }
}
