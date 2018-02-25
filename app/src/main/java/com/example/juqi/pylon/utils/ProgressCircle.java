package com.example.juqi.pylon.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

/**
 * Created by Lio_Zhuo on 2016/7/26 0026.
 */
public class ProgressCircle {

    private Context context;
    private int color;
    private final int backgroundColor = 0x00ffffff;
    private ImageView imageView;
    private MaterialProgressDrawable mProgress;
    private ValueAnimator valueAnimator;
    boolean start = false;
    boolean visible = false;

    public ProgressCircle(Context context, int color, ImageView imageView){
        this.context = context;
        this.color = color;
        this.imageView = imageView;
    }

    public void setCircle(){
        mProgress = new MaterialProgressDrawable(context,imageView);
        mProgress.setBackgroundColor(backgroundColor);
        mProgress.setColorSchemeColors(color);
        mProgress.updateSizes(MaterialProgressDrawable.DEFAULT);
        imageView.setImageDrawable(mProgress);

        if(valueAnimator == null)
        {
            valueAnimator = valueAnimator.ofFloat(0f,1f);
            valueAnimator.setDuration(600);
            valueAnimator.setInterpolator(new DecelerateInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float n = (float) animation.getAnimatedValue();
                    //圈圈的旋转角度
                    mProgress.setProgressRotation(n * 0.5f);
                    //圈圈周长，0f-1F
                    mProgress.setStartEndTrim(0f, n * 0.8f);
                    //箭头大小，0f-1F
                    mProgress.setArrowScale(n);
                    //透明度，0-255
                    mProgress.setAlpha((int) (255 * n));
                }
            });
            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    visible = true;
                }
            });
        }

        if(!valueAnimator.isRunning())
        {
            if(!visible)
            {
                //是否显示箭头
                mProgress.showArrow(false);
                valueAnimator.start();
            }
        }
    }

    public void startCircle(){
        if (!start)
        {
            mProgress.start();
            imageView.setVisibility(View.VISIBLE);
            start = true;
        }
    }

    public void stopCircle(){
        if (start)
        {
            mProgress.stop();
            start = false;
            visible = false;
            imageView.setVisibility(View.GONE);
        }
    }

}
