package com.example.administrator.colorprogressbar.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.colorprogressbar.R;

/**
 * Created by Administrator on 15-5-21.
 */
public class CountDownProgressBar extends View{
    private static final int DIAMETER = 100;
    private static final long TIME = 10000;
    private float progress =  0;
    private float startAngle = 270;
    private Paint paint;
    public CountDownProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(R.color.progresbae_color);
        paint.setStrokeWidth(6.0f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStyle(Paint.Style.STROKE);
        RectF oval = new RectF();
        oval.left = 6.0f;
        oval.right =DIAMETER;
        oval.top = 6.0f;
        oval.bottom =DIAMETER;
        canvas.drawArc(oval, startAngle,  360-progress, false, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(DIAMETER+20,DIAMETER+20);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,360);
        valueAnimator.setDuration(TIME);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress=(float)animation.getAnimatedValue();
                invalidate();
                if(progress==360){
                    setVisibility(GONE);
                }
            }
        });
        valueAnimator.start();

    }
    public void show(){
        setVisibility(VISIBLE);
        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,360);
        valueAnimator.setDuration(TIME);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress=(float)animation.getAnimatedValue();
                invalidate();
                if(progress==360){
                    setVisibility(GONE);
                }
            }
        });
        valueAnimator.start();
    }
}
