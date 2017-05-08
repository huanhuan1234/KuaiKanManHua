package xunqaing.bwie.com.kuaikanmanhua.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import xunqaing.bwie.com.kuaikanmanhua.R;


//发现
//分类接口
//        http://api.kuaikanmanhua.com/v1/topic_new/lists/get_by_tag?tag=0&since=0&count=20&gender=1&sort=1&sa_event=eyJkaXN0aW5jdF9pZCI6IkE6NjE0ODI3NzVmNTZkNWY4MiIsImV2ZW50IjoiUmVhZEZpbmRQYWdlIiwib3JpZ2luYWxfaWQiOiJBOjYxNDgyNzc1ZjU2ZDVmODIiLCJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJwcm9wZXJ0aWVzIjp7IkNhdGVnb3J5Ijoi5YWo6YOoIiwiRmluZENhdGVnb3J5VGFiTmFtZSI6IuWFqOmDqCIsIkZpbmRUYWJOYW1lIjoi5YiG57G7IiwiRnJvbUZpbmRDYXRlZ29yeVRhYk5hbWUiOiLlhajpg6giLCJGcm9tRmluZFRhYk5hbWUiOiLmjqjojZAiLCJHZW5kZXJUeXBlIjoi55S354mIIiwiSG9tZXBhZ2VUYWJOYW1lIjoi54Ot6ZeoIiwiSG9tZXBhZ2VVcGRhdGVEYXRlIjoxLCJJc0F1dG9Mb2FkIjpmYWxzZSwiVHJpZ2dlclBhZ2UiOiJIb21lUGFnZSIsIlZDb21tdW5pdHlUYWJOYW1lIjoi54Ot6ZeoIiwiUHJvcGVydHlFdmVudCI6IlJlYWRGaW5kUGFnZSIsImFidGVzdF9ncm91cCI6MTQsIiRhcHBfdmVyc2lvbiI6IjQuMC4wIiwiJGxpYl92ZXJzaW9uIjoiMS42LjM0IiwiJG1hbnVmYWN0dXJlciI6IkhVQVdFSSIsIiRtb2RlbCI6Ik5FTS1BTDEwIiwiJG9zIjoiQW5kcm9pZCIsIiRvc192ZXJzaW9uIjoiNi4wIiwiJHNjcmVlbl9oZWlnaHQiOjE4MTIsIiRzY3JlZW5fd2lkdGgiOjEwODAsIiR3aWZpIjp0cnVlLCIkY2FycmllciI6IuS4reWbveeUteS_oSIsIiRuZXR3b3JrX3R5cGUiOiJXSUZJIn0sInRpbWUiOjE0OTMyMDc3MTgzNDUsInR5cGUiOiJ0cmFjayJ9


public class FeedFragment extends Fragment {

    private RelativeLayout retitle;
    private ViewPager vp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.feed_fragment,container,false);
        retitle = (RelativeLayout) view.findViewById(R.id.pub_title__feed_id);
        vp = (ViewPager) view.findViewById(R.id.feed_fragment_viewpager);

        return view ;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }
}
