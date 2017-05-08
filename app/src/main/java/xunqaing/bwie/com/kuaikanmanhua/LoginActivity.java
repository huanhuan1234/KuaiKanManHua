package xunqaing.bwie.com.kuaikanmanhua;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginActivity extends Activity {

    private ImageView loginback;
    private EditText phoneedit;
    private ImageView girlandboy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginback = (ImageView) findViewById(R.id.login_back);
        phoneedit = (EditText) findViewById(R.id.phone_deit);
        girlandboy = (ImageView) findViewById(R.id.girlandboy);

        phoneedit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                 if(hasFocus){
                     girlandboy.setImageResource(R.drawable.ic_login_visible);

                 }else{
                     girlandboy.setImageResource(R.drawable.ic_login_invisible);
                 }


            }
        });

      phoneedit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              if(phoneedit.isFocused()){
                   girlandboy.setImageResource(R.drawable.ic_login_visible);
              }else{
                  girlandboy.setImageResource(R.drawable.ic_login_invisible);
              }
          }
      });






     loginback.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             finish();
         }
     });

    }



}
