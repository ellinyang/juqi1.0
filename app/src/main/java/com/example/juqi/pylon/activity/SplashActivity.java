package com.example.juqi.pylon.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.juqi.pylon.R;

/**
 * Created by ylj_y on 2016/9/29.
 */
public class SplashActivity extends Activity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();//结束本Activity
            }
        },500);//设置时间
    }
}