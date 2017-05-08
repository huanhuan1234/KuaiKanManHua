package xunqaing.bwie.com.kuaikanmanhua.task;

import android.os.AsyncTask;
import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import xunqaing.bwie.com.kuaikanmanhua.utils.StringUtils;

public class IAsyncTask extends AsyncTask<String,Void,String> {

    //通过接口回调，首先要接收它
    private ResponseListener listener ;

    public  IAsyncTask(ResponseListener listener){

        this.listener= listener ;

    }

    private URL url;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        String result = "" ;

        String path = params[0] ;
        InputStream inputStream = null ;

        //如果路径为空，直接返回空
        if(TextUtils.isEmpty(path)){
            return result;
        }


        try {
            URL url =   new URL(path);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection() ;

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);
            if(connection.getResponseCode() == 200){
                inputStream =  connection.getInputStream() ;
                result =  StringUtils.inputStreamToString(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(inputStream != null){
                    inputStream.close();
                    inputStream = null;

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  result ;
        }
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        //如果
        if(listener != null){


            if(TextUtils.isEmpty(result)){
                //回调失败
                listener.onFail();
            }else{
                // 回调成功

                listener.onSuccess(result);
            }
        } else {
            listener.onFail();
        }



    }

    //取消掉，执行失败方法
    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);

        if(listener != null){
            listener.onFail();
        }

    }
}
