package com.anwesome.ui.terminalview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by anweshmishra on 04/05/17.
 */
public class LineIndicator {
    private float x,y,size;
    public void setDimension(float x,float y,float w) {
        this.x = x;
        this.y = y;
        this.size = w;
    }
    public void draw(Canvas canvas, Paint paint){
        paint.setColor(Color.WHITE);
        for(int i=0;i<2;i++) {
            canvas.save();
            canvas.drawLine(x,y+2*size*i,x+size,y+size,paint);
            canvas.restore();
        }
    }
    public void updateY(float yGap) {
        y+=yGap;
    }
}
