package com.anwesome.ui.terminalview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 04/05/17.
 */
public class Cursor {
    private float x,y,w;
    public void setDimension(float x,float y,float w) {
        this.x = x;
        this.y = y;
        this.w = w;
    }
    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.WHITE);
        canvas.save();
        canvas.translate(x,y);
        canvas.drawLine(0, 0, w, 0, paint);
        canvas.restore();
    }
    public void updateXY(float w) {
        if(x+this.w >= w) {
            x = 0;
            y = y+3*this.w/2;
        }
        else {
            x = x+this.w;
        }
    }
    public int hashCode() {
        return (int)(x+y+w);
    }
}
