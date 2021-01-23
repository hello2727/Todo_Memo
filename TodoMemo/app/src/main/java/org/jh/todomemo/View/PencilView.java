package org.jh.todomemo.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PencilView extends View {
    Paint paint;
    Paint eraser;
    Canvas mCanvas;
    Bitmap mBitmap;

    float lastX;
    float lastY;

    boolean IsEraserOn;

    public PencilView(Context context) {
        super(context);

        init(context);
    }

    public PencilView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context) {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(7f);
        paint.setStyle(Paint.Style.STROKE);

        eraser = new Paint();
        eraser.setColor(Color.TRANSPARENT);
        eraser.setStrokeWidth(20f);
        IsEraserOn = false;

        //이전 좌표값
        lastX = -1;
        lastY = -1;
    }

    //뷰에 사이즈가 정해졌을 때 만들어지는 함수(메모리에 비트맵 객체 하나 만들어줌)
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas();
        mCanvas.setBitmap(mBitmap);

        mCanvas.drawColor(Color.TRANSPARENT);
    }

    public void setColor(int pickedColor){
        paint.setColor(pickedColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0, 0, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (lastX != -1) {
                    if (x != lastX || y != lastY) {
                        mCanvas.drawLine(lastX, lastY, x, y, paint);
                    }
                }

                //현재 값 넣어줌
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if (lastX != -1) {
                    mCanvas.drawLine(lastX, lastY, x, y, paint);
                }

                lastX = x;
                lastY = y;

                break;
            case MotionEvent.ACTION_UP:
                lastX = -1;
                lastY = -1;
                break;
        }

        invalidate();
        return true;
    }
}
