package xunqaing.bwie.com.kuaikanmanhua;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashActivity extends Activity {

     Handler handler = new Handler(){

         @Override
         public void handleMessage(Message msg) {
             super.handleMessage(msg);

             switch (msg.what){

                 case 0:

             Intent i = new Intent(SplashActivity.this,MainActivity.class);
             startActivity(i);

                     break;
             }
         }
     };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler.sendEmptyMessageDelayed(0,2000);

    }
}
