package com.example.myapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.myapplication.parallax.DensityUtil;

/**
 * Description:
 * Data：10/18/2018-10:42 AM
 *
 * @author yanzhiwen
 */
public class WaveView extends View {
    private Paint mPaint;
    private int mWidth;
    private int mHeight;
    private int mWaveHeight;
    private int mWaveDx;
    private int dx;

    private int deplayTime = 0;
    private int animatorTime = 2000;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.WaveView, defStyleAttr, 0);

        deplayTime = a.getInteger(R.styleable.WaveView_delay_time, 0);
        animatorTime = a.getInteger(R.styleable.WaveView_animator_time, 2000);
        int startColor = a.getColor(R.styleable.WaveView_start_color, ContextCompat.getColor(context, R.color.colorPrimary));
        int endColor = a.getColor(R.styleable.WaveView_end_color, ContextCompat.getColor(context, R.color.pressed_color));
        mWaveHeight = a.getDimensionPixelOffset(R.styleable.WaveView_wave_height, DensityUtil.dip2px(getContext(), 75));
        a.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        LinearGradient lg = new LinearGradient(0, -1000, 1000, -1000, startColor, endColor, Shader.TileMode.CLAMP);
        mPaint.setShader(lg);

        mPaint.setStyle(Paint.Style.FILL);
        //波长的的长度(这里设置为屏幕的宽度)
        mWaveDx = getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //控件的宽高
        mWidth = MeasureUtils.measureView(widthMeasureSpec, mWaveDx);
        mHeight = MeasureUtils.measureView(heightMeasureSpec, 300);
        //水波的高度
//        mWaveHeight = DensityUtil.dip2px(getContext(), 75);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawWave(canvas);
    }

    Path path;

    private void drawWave(Canvas canvas) {
        if (path == null)
            path = new Path();

        path.reset();
        path.moveTo(-mWaveDx + dx, mHeight * 0.5f);
        for (int i = -mWaveDx; i < getWidth() + mWaveDx; i += mWaveDx) {
            path.rQuadTo(mWaveDx * 0.25f, -mWaveHeight, mWaveDx * 0.5f, 0);
            path.rQuadTo(mWaveDx * 0.25f, mWaveHeight, mWaveDx * 0.5f, 0);
        }
        path.lineTo(mWidth, mHeight);
        path.lineTo(0, mHeight);
        //path.close() 绘制封闭的区域
        path.close();
        canvas.drawPath(path, mPaint);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (valueAnimator != null)
            valueAnimator.end();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (deplayTime == 0) {
            startAnimation();
        } else {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    startAnimation();
                }
            }, deplayTime);
        }
    }

    private ValueAnimator valueAnimator;

    public void startAnimation() {
        valueAnimator = ValueAnimator.ofInt(0, mWaveDx);
        valueAnimator.setDuration(animatorTime);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //水平方向的偏移量
                dx = (int) animation.getAnimatedValue();
                invalidate();
            }

        });
        valueAnimator.start();

    }
}
