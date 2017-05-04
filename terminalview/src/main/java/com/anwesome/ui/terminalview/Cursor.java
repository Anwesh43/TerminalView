package com.anwesome.ui.terminalview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 04/05/17.
 */
public class Cursor {
    private float x,y,w,indicator = 0;
    public void setDimension(float x,float y,float w) {
        this.x = x;
        this.y = y;
        this.w = w;
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.WHITE);
        canvas.save();
        canvas.translate(x,y);
        if(indicator%2 == 0) {
            canvas.drawLine(0, 0, w, 0, paint);
        }
        canvas.restore();
        indicator++;
    }
    public int hashCode() {
        return (int)(x+y+w);
    }
}
