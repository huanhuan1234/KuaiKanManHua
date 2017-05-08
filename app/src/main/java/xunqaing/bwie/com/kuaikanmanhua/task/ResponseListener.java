package xunqaing.bwie.com.kuaikanmanhua.task;



// 网络请求回调
public interface ResponseListener {


    //成功回调  获取String类型的值
    public void onSuccess(String string);

    // 失败回调
    public void onFail();

}
