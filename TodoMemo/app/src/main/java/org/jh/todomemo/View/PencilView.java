package org.jh.todomemo.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.net.Uri;
import android.nfc.Tag;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import org.jh.todomemo.R;

public class PencilView extends View {
    Paint paint;
    Paint eraser;
    Canvas mCanvas;
    Bitmap mBitmap;

    float lastX;
    float lastY;

    boolean IsEraserOn;

//    float bitmapCenterX;
//    float bitmapCenterY;
//    Bitmap sourceBitmap;
//    float sourceWidth = 0.0F;
//    float sourceHeight = 0.0F;
//    float displayWidth = 0.0F;
//    float displayHeight = 0.0F;
//    int displayCenterX = 0;
//    int displayCenterY = 0;

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
        paint.setAntiAlias(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);

        eraser = new Paint();
        eraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        eraser.setStrokeWidth(200f);
        IsEraserOn = false;

        //이전 좌표값
        lastX = -1;
        lastY = -1;
    }

    //뷰에 사이즈가 정해졌을 때 만들어지는 함수(메모리에 비트맵 객체 하나 만들어줌)
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

//        if(w > 0 && h > 0) {
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas();
            mCanvas.setBitmap(mBitmap);
            mCanvas.drawColor(Color.TRANSPARENT);

//            displayWidth = (float) w;
//            displayHeight = (float) h;
//
//            displayCenterX = w / 2;
//            displayCenterY = h / 2;
//        }

//        redraw(); //초기화(새로 그려주기)
    }

    public void setColor(int pickedColor){
        paint.setColor(pickedColor);
    }

//    public void setImageData(Bitmap image){
//        if(sourceBitmap != null){
//            sourceBitmap.recycle(); //비트맵 객체 해제
//        }
//
//        sourceBitmap = image;
//
//        sourceWidth = sourceBitmap.getWidth();
//        sourceHeight = sourceBitmap.getHeight();
//
//        bitmapCenterX = sourceBitmap.getWidth()/2;
//        bitmapCenterY = sourceBitmap.getHeight()/2;
//    }

//    public void redraw() {
//        if (sourceBitmap == null) {
//            Log.d("어쩌면", "sourceBitmap is null in redraw().");
//            return;
//        }
//
//        float originX = (displayWidth - (float)sourceBitmap.getWidth()) / 2.0F;
//        float originY = (displayHeight - (float)sourceBitmap.getHeight()) / 2.0F;
//
//        mCanvas.translate(originX, originY);
//
//        Matrix matrix = new Matrix();
//        Bitmap capturedBitmap = Bitmap.createBitmap(sourceBitmap, 0, 0, sourceBitmap.getWidth(), sourceBitmap.getHeight(), matrix, false);
//        mCanvas.drawBitmap(capturedBitmap, matrix, paint);
//
//        invalidate();
//    }

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
                        if(IsEraserOn){
                            mCanvas.drawLine(lastX, lastY, x, y, eraser);
                        }else{
                            mCanvas.drawLine(lastX, lastY, x, y, paint);
                        }
                    }
                }

                //현재 값 넣어줌
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if (lastX != -1) {
                    if(IsEraserOn){
                        mCanvas.drawLine(lastX, lastY, x, y, eraser);
                    }else{
                        mCanvas.drawLine(lastX, lastY, x, y, paint);
                    }
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
