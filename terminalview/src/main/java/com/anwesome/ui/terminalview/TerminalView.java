package com.anwesome.ui.terminalview;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 04/05/17.
 */
public class TerminalView extends View {
    private LineIndicator lineIndicator = new LineIndicator();
    private Cursor cursor = new Cursor();
    private int time = 0;
    public TerminalView(Context context){
        super(context);
    }
    public void onDraw(Canvas canvas) {
        int w = canvas.getWidth(),h = canvas.getHeight();
        if(time == 0) {
            lineIndicator.setDimension(0,0,w/20);
            cursor.setDimension(w/20,w/10,w/20);
        }
        time++;
        try {
            Thread.sleep(100);
            invalidate();
        }
        catch (Exception ex) {

        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
