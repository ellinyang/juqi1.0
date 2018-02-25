package com.example.juqi.pylon.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.juqi.pylon.R;

public class TitleActivity extends Activity {

    private TextView titleTextView;
    private ImageView imgBack;
    private FrameLayout contentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_title);
        titleTextView = (TextView)findViewById(R.id.text_title);
        imgBack = (ImageView)findViewById(R.id.image_back);
        contentLayout = (FrameLayout)findViewById(R.id.layout_content);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("title","finish");
                finish();
            }
        });
    }

    @Override
    public void setTitle(int titleId){

        Log.d("title","setTitle");
        titleTextView.setText(titleId);
    }

    @Override
    public void setTitle(CharSequence title){
        Log.d("title","setTitle");
        titleTextView.setText(title);
    }

    @Override
    public void setContentView(int layoutResID){
        contentLayout.removeAllViews();
        View.inflate(this,layoutResID,contentLayout);
        onContentChanged();
    }

    @Override
    public void setContentView(View view) {
        contentLayout.removeAllViews();
        contentLayout.addView(view);
        onContentChanged();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        contentLayout.removeAllViews();
        contentLayout.addView(view, params);
        onContentChanged();
    }

}
