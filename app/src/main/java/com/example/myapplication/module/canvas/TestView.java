package com.example.myapplication.module.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;

public class TestView extends View {

    private Paint paint;

    public TestView(Context context) {
        this(context,null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint(){
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        canvas.drawCircle(width / 2,width / 2,width / 2,paint);

        paint.setColor(Color.BLUE);
        canvas.drawLine(width/2,width/8,width/2,0,paint);
        canvas.save();
        for(int i =0; i <3; i++) {
            canvas.rotate(30, width /2, width /2);
            canvas.drawLine(width /2, width /8, width /2,0, paint);
        }

        paint.setStrokeWidth(10);
        paint.setColor(Color.YELLOW);
        canvas.drawLine(width/2,width/2,width/2,width/4, paint);

        canvas.restore();

        paint.setStrokeWidth(10);
        paint.setColor(Color.RED);
        canvas.drawLine(width/2,width/2,width/2,width/4, paint);
    }
}
