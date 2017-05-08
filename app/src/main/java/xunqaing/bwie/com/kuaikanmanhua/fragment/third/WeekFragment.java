package xunqaing.bwie.com.kuaikanmanhua.fragment.third;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import xunqaing.bwie.com.kuaikanmanhua.R;
import xunqaing.bwie.com.kuaikanmanhua.adapter.WeekFragmentAdapter;
import xunqaing.bwie.com.kuaikanmanhua.bean.WeekBean;
import xunqaing.bwie.com.kuaikanmanhua.event.ScrollEvent;
import xunqaing.bwie.com.kuaikanmanhua.task.IAsyncTask;
import xunqaing.bwie.com.kuaikanmanhua.task.ResponseListener;

/**
 * 1.android.support.v4.widget.SwipeRefreshLayout  下拉出现转圈
 *
 * 实现一个接口  SwipeRefreshLayout.OnRefreshListener  重写onRefresh方法
 *
 * 2.WeekFragment滑动，发出事件，传给 HomeFragment（订阅@subscribe）
 * 1.创建一个类，变量 标记一下这个事件要干啥
 * 2.HomeFragment
 *
 * //订阅接收 WeekFragment 的改变
 * //改方法在主线程执行
 * //参数：向上还是向下滑动
 * //注解：@Subscribe 订阅哪个类的改变 需要配置 threadMode 注解规定方法还能获取值
 *
 * @Subscribe(threadMode = ThreadMode.MAIN)
 * public void onScrollEvent(ScrollEvent event) {
 * System.out.println("event = " + event.isUp());
 * }
 *
 *
 * 3.注册这个事件 在 HomeFragment 类中
 *  在onCreateView方法中
EventBus.getDefault().register(this);



 @Override
 public void onStop() {
 super.onStop();

 EventBus.getDefault().unregister(this);
 }

在下拉方法中onRefresh传值：EventBus.getDefault().post(new ScrollEvent(true));
 *
 */

public class WeekFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ResponseListener {

    //设置标题值
    String title;
    private ListView lv;
    private SwipeRefreshLayout swipeRefreshLayout;
    private WeekFragmentAdapter adapter ;
    private List<WeekBean.DataBean.ComicsBean> list = new ArrayList<WeekBean.DataBean.ComicsBean>();

    //定义
    int index = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.weekfragment, container, false);

        //获取从HotFragmentAdapter传过来的值
        if (getArguments() != null) {

            title = getArguments().getString("title");
        }

        //找控件
        init(view);

        return view;
    }

    //找控件
    private void init(View view) {

        lv = (ListView) view.findViewById(R.id.week_listview);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.week_swiperefershlayout);

        //可以进行下拉刷新
        swipeRefreshLayout.setOnRefreshListener(this);

        //开启和关闭
        // swipeRefreshLayout.setEnabled();

        adapter = new WeekFragmentAdapter(this.getActivity(),list);
        lv.setAdapter(adapter);

        // 发送网络请求
        getData();

        //实现下拉 HomeFragment 隐藏

        /**
         * 第一是静止状态，SCROLL_STATE_IDLE
         第二是手指滚动状态，SCROLL_STATE_TOUCH_SCROLL
         第三是手指不动了，但是屏幕还在滚动状态。惯性SCROLL_STATE_FLING
         *
         */

        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                //手指按上滚动
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){

                    //当前手指按下后可见最后一条
                   index = lv.getLastVisiblePosition();
                }

                //停止滚动
                if (scrollState == SCROLL_STATE_IDLE){

                    //上滑  发送让头隐藏
                    if (lv.getLastVisiblePosition() - index >= 0){

                        EventBus.getDefault().post(new ScrollEvent(true));

                    }else{  //下拉

                        EventBus.getDefault().post(new ScrollEvent(false));
                    }

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });


    }

    // 实现接口重写的方法 --- 下拉
    @Override
    public void onRefresh() {

        getData();

    }


    // 发送网络请求
    private void getData() {

        String path = "http://api.kuaikanmanhua.com/v1/daily/comic_lists/1489334400?since=0&gender=0&sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNDg5NjQzNTYxODM4LCJwcm9wZXJ0aWVzIjp7IkhvbWVwYWdlVGFiTmFtZSI6IueDremXqCIsIlZDb21tdW5pdHlUYWJOYW1lIjoi54Ot6ZeoIiwiJG9zX3ZlcnNpb24iOiI0LjQuMiIsIkdlbmRlclR5cGUiOiLlpbPniYgiLCJGcm9tSG9tZXBhZ2VUYWJOYW1lIjoi54Ot6ZeoIiwiJGxpYl92ZXJzaW9uIjoiMS42LjM0IiwiJG5ldHdvcmtfdHlwZSI6IldJRkkiLCIkd2lmaSI6dHJ1ZSwiJG1hbnVmYWN0dXJlciI6IkxFTk9WTyIsIkZyb21Ib21lcGFnZVVwZGF0ZURhdGUiOjMsIiRzY3JlZW5faGVpZ2h0IjoxNDQwLCJIb21lcGFnZVVwZGF0ZURhdGUiOjMsIlByb3BlcnR5RXZlbnQiOiJSZWFkSG9tZVBhZ2UiLCJGaW5kVGFiTmFtZSI6IuaOqOiNkCIsImFidGVzdF9ncm91cCI6MTAsIiRzY3JlZW5fd2lkdGgiOjkwMCwiJG9zIjoiQW5kcm9pZCIsIlRyaWdnZXJQYWdlIjoiSG9tZVBhZ2UiLCIkY2FycmllciI6IkNISU5BIE1PQklMRSIsIiRtb2RlbCI6Ikxlbm92byBQNzAtdCIsIiRhcHBfdmVyc2lvbiI6IjMuOS4yIn0sInR5cGUiOiJ0cmFjayIsImRpc3RpbmN0X2lkIjoiQTo1MDdCOUQyN0Q2NUEwMDAwIiwib3JpZ2luYWxfaWQiOiJBOjUwN0I5RDI3RDY1QTAwMDAiLCJldmVudCI6IlJlYWRIb21lUGFnZSJ9";
        new IAsyncTask(this).execute(path);
    }


    // 网络请求回调
    @Override
    public void onSuccess(String string) {

        swipeRefreshLayout.setRefreshing(false);

        Gson gson = new Gson();
        WeekBean bean =  gson.fromJson(string, WeekBean.class);

        list.addAll(bean.getData().getComics());

        adapter.notifyDataSetChanged();

    }

    @Override
    public void onFail() {

    }
}
