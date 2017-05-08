package xunqaing.bwie.com.kuaikanmanhua.fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xunqaing.bwie.com.kuaikanmanhua.R;
import xunqaing.bwie.com.kuaikanmanhua.adapter.HomeFragmentAdapter;
import xunqaing.bwie.com.kuaikanmanhua.event.ScrollEvent;
import xunqaing.bwie.com.kuaikanmanhua.task.ResponseListener;

/**
 * 或得返回值，实现接口
 */

public class HomeFragment extends Fragment implements ResponseListener {


    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private RelativeLayout relativeLayoutTitle;

    //第二步：订阅接收 WeekFragment 的改变
    //该方法在主线程执行（同样也可以在子线程运行）
    //参数：向上还是向下滑动
    //注解：@Subscribe 订阅哪个类的改变 需要配置 threadMode
    // 注解规定方法还能获取值 ：up的值 （传过来的是true还是false）

    /**
     * 粘性 ---EventBus还支持发送黏性事件。何为黏性事件呢？简单讲，就是在发送事件之后再订阅该事件也能收到该事件，跟黏性广播类似。
     * <p>
     * 一些事件进行信息感兴趣的事件后发布。 例如,一个事件信号,一些初始化完成。 或者如果你有传感器位置数据和你想抓住最近的值。 而不是实现自己的缓存,您可以使用黏性的事件。
     * EventBus保持过去的事件的特定类型在内存中。 黏性的事件可以交付给用户或显式查询。 因此,你不需要任何特殊的逻辑来考虑可用的数据。
     * <p>
     * 第一步：
     * EventBus.getDefault().postSticky(new MessageEvent("Hello everyone!"));
     * <p>
     * 第二步：现在,粘性事件发布,开始一个新的活动。 在注册过程中粘性用户所有方法将立即得到前面贴粘性的事件:
     * <p>
     * 首先需要注册订阅事件
     *
     * @Override public void onStart() {
     * super.onStart();
     * EventBus.getDefault().register(this);
     * }
     * @Subscribe(sticky = true, threadMode = ThreadMode.MAIN) ／／这种写法达到粘性的目的
     * public void onEvent(MessageEvent event) {
     * // UI updates must run on MainThread
     * textField.setText(event.message);
     * }
     * <p>
     * 然后在onDestory()方法中取消订阅：防止内存溢出
     * @Override public void onStop() {
     * EventBus.getDefault().unregister(this);
     * super.onStop();
     * }
     * 这样就达到了目的：发送事件之后再订阅该事件也能收到该事件
     * <p>
     * <p>
     * 最后说一句，配合着Activity或者fragment的生命周期会获得意想不到的结果。
     * 当然如果当前的Activity或者fragment已经结束了（调用onStop（）），
     * 只要是使用的是EventBus的粘性事件也可以执行已经结束了生命周期里的方法，前提就是@Subscribe(sticky = true)；
     * <p>
     * <p>
     * 先发送，在接受也可以
     */



    @Subscribe(threadMode = ThreadMode.MAIN, sticky = false)
    public void onScrollEvent(ScrollEvent event) {

        //如果是下拉，让其隐藏
        if (event.isUp()) {

            //如果已经隐藏
            if (relativeLayoutTitle.getVisibility() == View.GONE) {
                return;
            }

            /**
             * 只是为了不生硬
             *
             * 1.哪个控件 2.什么轴 3.public float getY() {
             return mTop + getTranslationY();
             } 4.如果隐藏还剩下的高度
             *
             *  relativeLayoutTitle.getY() - relativeLayoutTitle.getHeight()  ---
             *  获取当前y轴 - 控件高度
             */
            ObjectAnimator anim = ObjectAnimator.ofFloat(relativeLayoutTitle, "y", relativeLayoutTitle.getY(),
                    relativeLayoutTitle.getY() - relativeLayoutTitle.getHeight());
            anim.setDuration(500);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    relativeLayoutTitle.setVisibility(View.GONE);

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            anim.start();

        } else {  //上滑，显示

            if (relativeLayoutTitle.getVisibility() == View.VISIBLE) {
                return;
            }

            relativeLayoutTitle.setVisibility(View.VISIBLE);

            ObjectAnimator anim = ObjectAnimator.ofFloat(relativeLayoutTitle, "y", relativeLayoutTitle.getY(),
                    relativeLayoutTitle.getY() + relativeLayoutTitle.getHeight());

            anim.setDuration(500);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    relativeLayoutTitle.setVisibility(View.VISIBLE);

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            anim.start();

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);

        initView(view);
        //并行 ，默认串行，如果一个页面数据很多，第一个页面没刷新出来，第二个页面就要等待，所以调用并行
        //BaseActivity调用线程池，第二个是网址
        //        new IAsyncTask(this).executeOnExecutor(BaseActivity.threadPoolExecutor,"");

        return view;
    }

    private void initView(View view) {

        viewPager = (ViewPager) view.findViewById(R.id.home_fragment_viewpager);
        relativeLayoutTitle = (RelativeLayout) view.findViewById(R.id.pub_title_id);

        HomeFragmentAdapter adapter = new HomeFragmentAdapter(getActivity().getSupportFragmentManager());

        viewPager.setAdapter(adapter);

        //关注和热门
        radioGroup = (RadioGroup) view.findViewById(R.id.pub_radiogroup);

        // 设置 显示 热门 fragment
        viewPager.setCurrentItem(1);

        //这是 热门 这个radiobutton 选中状态
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {

                    case R.id.pub_title_left:
                        viewPager.setCurrentItem(0);
                        break;

                    case R.id.pub_title_right:
                        viewPager.setCurrentItem(1);
                        break;

                }
            }
        });

        //滑动时关注和热门改变
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (position == 0) {
                    radioGroup.check(R.id.pub_title_left);
                } else {
                    radioGroup.check(R.id.pub_title_right);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //第三步：注册一下
        EventBus.getDefault().register(this);

    }

    //如果界面不可见，取消注册
    @Override
    public void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    //成功的回调
    @Override
    public void onSuccess(String string) {

    }

    //失败的回调
    @Override
    public void onFail() {

    }
}
