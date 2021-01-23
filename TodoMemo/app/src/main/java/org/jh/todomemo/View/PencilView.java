package org.jh.todomemo.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

class Point{
    float x;
    float y;
    int pValue; //손가락터치 상태
    public Point(float x, float y, int value){
        this.x = x;
        this.y = y;
        pValue = value;
    }
}
class Scale{
    int start, end, color; //DrawImage의 범위 및 색상
    public Scale(int start, int end, int color){
        this.start = start;
        this.end = end;
        this.color = color;
    }
}

public class PencilView extends View {
    Paint paint;
    ArrayList<Point> arrP; //Point 클래스 저장.
    ArrayList<Scale> arrS; //Scale 클래스 저장.
    final int WAIT = -1;
    final int START = 0; //터치상태(down)
    final int MOVE = 1; //터치상태(move)
    int value, color, reStart, reEnd; //터치상태 저장변수, drawimage 색상, drawimage의 시작값 대입변수, drawimage의 끝값 변수

    Bitmap bit;

    public PencilView(Context context) {
        super(context);

        paint = new Paint();
        arrP = new ArrayList<>();
        arrS = new ArrayList<>();
        color = Color.BLACK;
        bit = null;
//        paint.setColor(Color.BLACK);
    }

    public void setColor(int pickedColor){
        color = pickedColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.TRANSPARENT);
        reEnd = arrP.size();
        arrS.add(new Scale(reStart, reEnd, color));

        if(bit != null){
            canvas.drawBitmap(bit, null, new Rect(0, 0, 600, 600), null);
        }

        for(int j = 0; j < arrS.size(); j++){
            paint.setColor(arrS.get(j).color);
            paint.setStrokeWidth(3f);

            //arrS에 저장된 각 grawImage의 범위만큼만 loop 돌면서 image 그림.
            for(int i = arrS.get(j).start; i < arrS.get(j).end; i++){
                if(arrP.get(i).pValue == MOVE){
                    canvas.drawLine(arrP.get(i-1).x, arrP.get(i-1).y, arrP.get(i).x, arrP.get(i).y, paint);
                }

                if(i == arrP.size() - 1){ //마지막에 그려진 drawimage의 끝 값이
                    reStart = arrP.size(); //다음 drawimage의 처음 값으로 저장.
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                value = START;
                arrP.add(new Point(x, y, value));
                break;
            case MotionEvent.ACTION_MOVE:
                value = MOVE;
                arrP.add(new Point(x, y, value));
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }
}
