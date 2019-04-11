package com.sheygam.java_23_10_04_19;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class MyFragment extends Fragment implements View.OnClickListener {
    private float scale;
    private int color;
    private Callback callback;

    public static MyFragment instance(float scale, int color){
        MyFragment fragment = new MyFragment();
//        fragment.color = color;
//        fragment.scale = scale;
        Bundle args = new Bundle();
        args.putInt("COLOR",color);
        args.putFloat("SCALE",scale);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            color = args.getInt("COLOR");
            scale = args.getFloat("SCALE");
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment,
                container,false);

        Button myBtn = view.findViewById(R.id.myBtn);
        myBtn.setOnClickListener(this);
//        Random rnd = new Random();
//        int r = rnd.nextInt(256);
//        int g = rnd.nextInt(256);
//        int b = rnd.nextInt(256);

        view.setBackgroundColor(color);
        view.setScaleY(scale);
        view.setScaleX(scale);
//        SCALE -= 0.1;
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("MY_TAG", "onAttach Context: ");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("MY_TAG", "onAttach Activity: ");
        if(!(activity instanceof Callback)){
            throw new IllegalArgumentException(activity.getClass().getSimpleName() +
                    " Must implements Callback interface");
        }
        callback = (Callback) activity;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.myBtn){
//            Toast.makeText(getContext(), "Click", Toast.LENGTH_SHORT).show();
            if (callback!=null) {
                callback.onBtnClicked();
            }
        }
    }

    public interface Callback{
        void onBtnClicked();
    }
}
