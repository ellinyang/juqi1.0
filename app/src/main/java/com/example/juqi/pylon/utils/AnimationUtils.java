package com.example.juqi.pylon.utils;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by Lio_Zhuo on 2016/6/8 0008.
 */
public class AnimationUtils {

    public static void touchAnimate(final View target, final Object start, final Object end, final int duration){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1,100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private ArgbEvaluator evaluator = new ArgbEvaluator();
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentvalue = (Integer)animation.getAnimatedValue();
                float fraction = animation.getAnimatedFraction();
                target.setBackgroundColor((Integer)evaluator.evaluate(fraction,start,end));
                target.setBackgroundColor((Integer)evaluator.evaluate(fraction,end,start));
                target.requestLayout();

            }
        });
        valueAnimator.setDuration(duration).start();
    }

}
