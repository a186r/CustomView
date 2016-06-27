package com.durs.mydraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by durs on 2016/6/27.
 */

public class LayerView extends View {

    private Paint mPaint;
    private int mViewHeight,mViewWidth; //控件宽高

    public LayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //实例化paint并设置其标识值
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewHeight = h;
        mViewWidth = w;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        /**
         * 绘制一个红色矩形
         */
        mPaint.setColor(Color.RED);
        /**
         * canvas一直称之为画布,其实更像是一个容器,如果吧canvas理解成画板,那么"层"就相当于是夹在画板上的一张张透明的纸,
         * 在android上就是一个个封装在canvas中的bitmap.
         */
        canvas.drawRect(mViewWidth / 2f - 200,mViewHeight / 2f - 200,mViewWidth/2f +200 , mViewHeight/2f+200,mPaint);

        /**
         * 保存画布,并绘制一个蓝色矩形
         */
        /**
         * canvas.save():
         *      在当前canvas的bitmap做操作,只能针对bitmap的形变和裁剪做操作
         * canvas.asveXxxx():
         *      将所有的操作存到一个新的bitmap,而不影响当前canvas的bitmap,无所不能
         *
         * 两者还有很多不同,虽然差别大,但是两者在一般应用上所能实现的功能是差不多的.
         */
//      canvas.save();
//        canvas.saveLayer(0,0,mViewWidth,mViewHeight,mPaint);
        canvas.saveLayer(mViewWidth/2-100,mViewHeight/2-100,mViewWidth/2+100,mViewHeight/2+100,null,canvas.ALL_SAVE_FLAG);//之保存要保存的区域
        mPaint.setColor(Color.BLUE);
        canvas.rotate(8);//旋转画布30度
        canvas.drawRect(mViewWidth /2f -100,mViewHeight/2f -100,mViewWidth/2f +100,mViewHeight/2+100,mPaint);
        canvas.restore();
    }
}
