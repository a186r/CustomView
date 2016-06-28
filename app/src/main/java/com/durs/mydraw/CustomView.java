package com.durs.mydraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by durs on 2016/6/28.
 */
public class CustomView extends View implements Runnable {


    private Paint mPaint;
    private int radiu;

    /**
     * @param context 相当于一个信使,携带各类信息
     * @param attrs   AttributeSet类型的签名 解析各种属性
     */
    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        //实例化画笔并且打开抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        /**
         * 设置画笔描边
         *
         * 画笔样式分三种：
         * 1.Paint.Style.STROKE：描边
         * 2.Paint.Style.FILL_AND_STROKE：描边并填充
         * 3.Paint.Style.FILL：填充
         */
        mPaint.setStyle(Paint.Style.STROKE);

        //设置画笔颜色为浅灰
        mPaint.setColor(Color.LTGRAY);
        /**
         * 设置描边宽度(px)
         * 当setStrokeWidth为0的时候并不表示描边宽度为0,而是只占1个像素
         */
        mPaint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int w_screen = dm.widthPixels;
        int h_screen = dm.heightPixels;
        // 绘制圆环
        canvas.drawCircle(w_screen / 2, h_screen / 2, radiu, mPaint);
    }


    public synchronized void setRadiu(int radiu) {
        this.radiu = radiu;
        //重绘
        invalidate();

    }

    @Override
    public void run() {
        //确保不断执行绘制
        while (true) {
            try {
                if (radiu < 200) {
                    radiu += 10;
                    //刷新view
                    postInvalidate();
                } else {
                    radiu = 0;
                }

                //每20毫秒暂停一次
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
