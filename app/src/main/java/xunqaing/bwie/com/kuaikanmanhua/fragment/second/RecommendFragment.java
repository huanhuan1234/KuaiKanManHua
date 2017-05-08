package xunqaing.bwie.com.kuaikanmanhua.fragment.second;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import xunqaing.bwie.com.kuaikanmanhua.R;
import xunqaing.bwie.com.kuaikanmanhua.bean.SlideShow;
import xunqaing.bwie.com.kuaikanmanhua.listener.ResponseListener_find;
import xunqaing.bwie.com.kuaikanmanhua.task.IAsync;



public class RecommendFragment extends Fragment implements ResponseListener_find {

    private ViewPager viewPager;

    List<SlideShow.DataBean.InfosBean> listall=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_find_recommend);
        initData();

    if(listall.size()!=0){
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return listall.get(0).getBanners().size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(getActivity());


                ImageLoader.getInstance().displayImage(listall.get(0).getBanners().get(position
                ).getPic(), imageView);

                container.addView(imageView);


                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

                container.removeView((View) object);

            }
        });

    }
            return view;
        }

    private void initData() {

        IAsync iAsync=new IAsync(this);

        AsyncTask<String, Void, Object> execute = iAsync.execute("http://api.kkmh.com/v1/topic_new/discovery_list?gender=0&sa_event=eyJwcm9qZWN0Ijoia3VhaWthbl9hcHAiLCJ0aW1lIjoxNDg3ODM5MDM5MzE1LCJwcm9wZXJ0aWVzIjp7IkhvbWVwYWdlVGFiTmFtZSI6IueDremXqCIsIlZDb21tdW5pdHlUYWJOYW1lIjoi54Ot6ZeoIiwiJG9zX3ZlcnNpb24iOiI0LjIuMiIsIkdlbmRlclR5cGUiOiLlpbPniYgiLCJGcm9tRmluZENhdGVnb3J5VGFiTmFtZSI6IuWFqOmDqCIsIklzQXV0b0xvYWQiOmZhbHNlLCIkbGliX3ZlcnNpb24iOiIxLjYuMzQiLCIkbmV0d29ya190eXBlIjoiV0lGSSIsIiR3aWZpIjp0cnVlLCIkbWFudWZhY3R1cmVyIjoic2Ftc3VuZyIsIkZyb21GaW5kVGFiTmFtZSI6IuaOqOiNkCIsIiRzY3JlZW5faGVpZ2h0Ijo1NzYsIkNhdGVnb3J5Ijoi5peg5rOV6I635Y-WIiwiSG9tZXBhZ2VVcGRhdGVEYXRlIjowLCJQcm9wZXJ0eUV2ZW50IjoiUmVhZEZpbmRQYWdlIiwiRmluZFRhYk5hbWUiOiLmjqjojZAiLCJhYnRlc3RfZ3JvdXAiOjQ2LCIkc2NyZWVuX3dpZHRoIjoxMDI0LCJGaW5kQ2F0ZWdvcnlUYWJOYW1lIjoi5YWo6YOoIiwiJG9zIjoiQW5kcm9pZCIsIlRyaWdnZXJQYWdlIjoiSG9tZVBhZ2UiLCIkY2FycmllciI6IkNNQ0MiLCIkbW9kZWwiOiJHVC1QNTIxMCIsIiRhcHBfdmVyc2lvbiI6IjMuOC4xIn0sInR5cGUiOiJ0cmFjayIsImRpc3RpbmN0X2lkIjoiQTo5MDUxMDQyNzYzNzU1MTA5Iiwib3JpZ2luYWxfaWQiOiJBOjkwNTEwNDI3NjM3NTUxMDkiLCJldmVudCI6IlJlYWRGaW5kUGFnZSJ9");

        try {
            String s = (String) execute.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccess(Object result) {
      String s = (String) result;
        Gson gson=new Gson();
        SlideShow slideShow = gson.fromJson(s, SlideShow.class);

        System.out.println("slideShow = " + s);
        
       listall.addAll(slideShow.getData().getInfos());

    }

    @Override
    public void onFail() {

    }
}
