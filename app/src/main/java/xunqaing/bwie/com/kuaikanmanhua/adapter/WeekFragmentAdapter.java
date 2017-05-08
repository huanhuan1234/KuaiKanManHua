package xunqaing.bwie.com.kuaikanmanhua.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import xunqaing.bwie.com.kuaikanmanhua.IApplication;
import xunqaing.bwie.com.kuaikanmanhua.R;
import xunqaing.bwie.com.kuaikanmanhua.bean.WeekBean;


public class WeekFragmentAdapter extends BaseAdapter {


    private List<WeekBean.DataBean.ComicsBean> list ;
    private Context context;

    private LayoutInflater inflater ;

    public WeekFragmentAdapter(Context context, List<WeekBean.DataBean.ComicsBean> list){

        this.context =  context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder viewHolder = null ;

        if( convertView == null ){


            convertView = View.inflate(context,R.layout.week_item,null);


            viewHolder = new ViewHolder();

            viewHolder.week_item_type = (TextView) convertView.findViewById(R.id.week_item_type);
            viewHolder.week_item_title = (TextView) convertView.findViewById(R.id.week_item_title);

            viewHolder.week_item_imageview = (ImageView) convertView.findViewById(R.id.week_item_imageview);


            viewHolder.week_item_sub_title = (TextView) convertView.findViewById(R.id.week_item_sub_title);

            viewHolder.week_item_image_zan = (ImageView) convertView.findViewById(R.id.week_item_image_zan);
            viewHolder.week_item_zannum = (TextView) convertView.findViewById(R.id.week_item_zannum);

            viewHolder.week_item_image_comment = (ImageView) convertView.findViewById(R.id.week_item_image_comment);
            viewHolder.week_item_comment = (TextView) convertView.findViewById(R.id.week_item_comment);

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag() ;

        }

        //显示图片
        ImageLoader.getInstance().displayImage(list.get(position).getCover_image_url(),viewHolder.week_item_imageview, IApplication.getOption());

        viewHolder.week_item_type.setText(list.get(position).getLabel_text());
        viewHolder.week_item_title.setText(list.get(position).getTopic().getTitle());


        viewHolder.week_item_sub_title.setText(list.get(position).getTitle());
        viewHolder.week_item_zannum.setText(list.get(position).getLikes_count()+"");
        viewHolder.week_item_comment.setText(list.get(position).getComments_count()+"");

        // 设置 文字 背景色 ， 动态改变， 和服务器返回的颜色一致
        //字符串转成 int 类型  parseColor
        GradientDrawable gradientDrawable = (GradientDrawable) viewHolder.week_item_type.getBackground();
        gradientDrawable.setColor(Color.parseColor(list.get(position).getLabel_color()));

        //点赞动画
        viewHolder.week_item_image_zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**
                 * v --- 哪个图片
                 * scaleX --- 从哪个轴上，从多大放大到多少倍再缩回
                 *
                 */

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(ObjectAnimator.ofFloat(v,"scaleY",1,1.4f,1)
                ,ObjectAnimator.ofFloat(v,"scaleX",1,1.4f,1));

                animatorSet.setDuration(400);
                animatorSet.start();
            }
        });

        return convertView;
    }

    static class ViewHolder {

        TextView week_item_type;
        TextView week_item_title;

        ImageView week_item_imageview;

        TextView week_item_sub_title;

        ImageView week_item_image_zan;
        TextView week_item_zannum;

        ImageView week_item_image_comment;
        TextView week_item_comment;
    }
}
