package com.anwesome.ui.terminalview;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 04/05/17.
 */
public class TerminalView extends View {
    private LineIndicator lineIndicator = new LineIndicator();
    private Cursor cursor = new Cursor();
    private int time = 0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public TerminalView(Context context){
        super(context);
    }
    public void onDraw(Canvas canvas) {
        paint.setStrokeCap(Paint.Cap.ROUND);

        paint.setStrokeWidth(canvas.getWidth()/40);
        canvas.drawColor(Color.BLACK);
        int w = canvas.getWidth(),h = canvas.getHeight();
        if(time == 0) {
            lineIndicator.setDimension(0,0,w/20);
            cursor.setDimension(w/20,w/10,w/20);
        }
        canvas.save();
        canvas.translate(canvas.getWidth()/30,canvas.getHeight()/30);
        lineIndicator.draw(canvas,paint);
        cursor.draw(canvas,paint);
        canvas.restore();
        time++;
        try {
            Thread.sleep(500);
            invalidate();
        }
        catch (Exception ex) {

        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
