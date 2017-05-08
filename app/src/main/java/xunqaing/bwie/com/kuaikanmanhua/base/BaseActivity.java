package xunqaing.bwie.com.kuaikanmanhua.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import xunqaing.bwie.com.kuaikanmanhua.R;

//MainActivity 来继承这个类，执行并行方法
public class BaseActivity extends FragmentActivity {

    //线程池
    private static final int KEEP_ALIVE_SECONDS = 30;
    //队列
    private static final BlockingQueue<Runnable> sPoolWorkQueue =
            new LinkedBlockingQueue<Runnable>(128);
    /**
     * 线程数的个数
     *线程池中的最大的线程个数
     *队列
     */
    public static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            3, 10, KEEP_ALIVE_SECONDS, TimeUnit.SECONDS,
            sPoolWorkQueue);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

    }
}
