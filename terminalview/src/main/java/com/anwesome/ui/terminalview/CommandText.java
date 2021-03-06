package com.anwesome.ui.terminalview;

import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 04/05/17.
 */
public class CommandText {
    private float y,w;
    private Paint paint;
    public CommandText(float y,float w,Paint paint,char letter) {
        this.y = y;
        this.w = w;
        this.paint = paint;
        currText = new LineText(0,""+letter);
    }
    public float getEndY() {
        return this.y+(currText!=null?currText.y:0);
    }
    public float getEndX() {
        return currText == null?0:paint.measureText(currText.text);
    }
    private ConcurrentLinkedQueue<LineText> lineTexts = new ConcurrentLinkedQueue<>();
    private LineText currText;
    public void addWord(char unicodeChar) {
        String newText = currText.text+unicodeChar;
        if(paint.measureText(newText) > 17*w/20) {
            lineTexts.add(currText);
            currText = new LineText(currText.y+paint.getTextSize(),""+unicodeChar);
        }
        else {
            currText.updateText(unicodeChar);
        }
    }
    public int hashCode() {
        return lineTexts.hashCode()+(int)y;
    }
    public void drawText(Canvas canvas,Paint paint) {
        canvas.save();
        canvas.translate(0,y);
        for(LineText lineText:lineTexts) {
            lineText.draw(canvas);
        }
        if(currText!=null) {
            currText.draw(canvas);
        }
        canvas.restore();
    }
    private class LineText{
        private float y = 0;
        private String text="";
        public void updateText(char letter) {
            text += letter;
        }
        public LineText(float y,String text) {
            this.text = text;
            this.y = y;
        }
        public int hashCode() {
            return text.hashCode()+(int)y;
        }
        public void draw(Canvas canvas) {
            canvas.drawText(text,0,this.y,paint);
        }
    }
}
