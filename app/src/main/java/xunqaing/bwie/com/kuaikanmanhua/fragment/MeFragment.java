package xunqaing.bwie.com.kuaikanmanhua.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import xunqaing.bwie.com.kuaikanmanhua.R;
import xunqaing.bwie.com.kuaikanmanhua.utils.CirImageView;


public class MeFragment extends Fragment {

    private ListView lv;

    List<String> list = new ArrayList<>();
    private TextView tv;
    private ImageView iv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.me_fragment,container,false);

        lv = (ListView) view.findViewById(R.id.me_listview);

//        LayoutInflater lif = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View viewHead = lif.inflate(R.layout.me_listhead, lv, false);

        View viewHead = inflater.inflate(R.layout.me_listhead,null);

        tv = (TextView) viewHead.findViewById(R.id.me_tv);
        iv = (ImageView) viewHead.findViewById(R.id.me_iv);
        lv.addHeaderView(viewHead);

        tv.setText("孙寻南强");
        ImageLoader.getInstance().displayImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493725520844&di=7c22cfb7a20fd45adf4d884fa669f2c7&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F00666rjmjw1exyc1lt3dmj30go0ahacu.jpg", iv, CirImageView.MyDisplayImageOptions());


        list.add("我的消息");
        list.add("我的钱包");
        list.add("我的关注");
        list.add("我的收藏");
        list.add("浏览历史");
        list.add("游戏中心");
        list.add("快看商城");
        list.add("设置");

        return view ;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Myadapter adapter = new Myadapter();

        lv.setAdapter(adapter);

    }

    class Myadapter extends BaseAdapter{


        @Override
        public int getCount() {
            return list!=null?list.size():0;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder ;

            if (convertView == null){

                viewHolder = new ViewHolder();

                convertView = View.inflate(getActivity(), R.layout.me_list,null);

                viewHolder.tv = (TextView) convertView.findViewById(R.id.me_list_tv);

                convertView.setTag(viewHolder);

            }else{

                viewHolder = (ViewHolder) convertView.getTag();
            }


            for (int i = 0; i <list.size() ; i++) {
                viewHolder.tv.setText(list.get(position));
            }

            return convertView;
        }

        class ViewHolder{

            TextView tv;
        }
    }
}
