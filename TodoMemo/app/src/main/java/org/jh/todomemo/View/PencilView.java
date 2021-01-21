package org.jh.todomemo.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

public class PencilView extends View {
    Paint paint = new Paint();
    Path path = new Path(); //자취를 저장할 객체

    public PencilView(Context context) {
        super(context);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(7f);
        paint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        invalidate();
        return true;
    }
}
