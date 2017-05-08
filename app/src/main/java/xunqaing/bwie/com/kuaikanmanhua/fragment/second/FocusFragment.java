package xunqaing.bwie.com.kuaikanmanhua.fragment.second;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import xunqaing.bwie.com.kuaikanmanhua.LoginActivity;
import xunqaing.bwie.com.kuaikanmanhua.R;
public class FocusFragment extends Fragment {
ImageView imageView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_second_focus, container, false);

        imageView = (ImageView) view.findViewById(R.id.image_not_login);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
