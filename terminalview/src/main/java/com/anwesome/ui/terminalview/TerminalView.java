package com.anwesome.ui.terminalview;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 04/05/17.
 */
public class TerminalView extends View {
    private LineIndicator lineIndicator = new LineIndicator();
    private Cursor cursor = new Cursor();
    private CommandText currCommandText;
    private ConcurrentLinkedQueue<CommandText> commandTexts = new ConcurrentLinkedQueue<>();
    private int time = 0,w,h;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public TerminalView(Context context){
        super(context);
        setFocusableInTouchMode(true);
        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction() == KeyEvent.ACTION_DOWN && !(keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK)) {
                    if(keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                        cursor.resetCursor();
                        lineIndicator.updateY(cursor.getY());
                        commandTexts.add(currCommandText);
                        currCommandText = null;
                    }
                    else {

                        char unicodeChar = (char)keyEvent.getUnicodeChar();

                        if(currCommandText == null) {
                            currCommandText = new CommandText(cursor.getY(),w,paint,unicodeChar);
                        }
                        else {
                            currCommandText.addWord(unicodeChar);
                        }
                        cursor.updateXY(currCommandText.getEndX(),currCommandText.getEndY());
                    }
                    postInvalidate();
                }
                return false;
            }
        });
    }
    public void onDraw(Canvas canvas) {
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(canvas.getWidth()/40);
        paint.setColor(Color.WHITE);
        canvas.drawColor(Color.BLACK);
        w = canvas.getWidth();
        h = canvas.getHeight();
        if(time == 0) {

            lineIndicator.setDimension(0,0,w/20);
            cursor.setDimension(w/10,w/10,w/20);
        }
        paint.setTextSize(w/10);
        canvas.save();
        canvas.translate(canvas.getWidth()/30,canvas.getHeight()/30);
        lineIndicator.draw(canvas,paint);
        cursor.draw(canvas,paint);
        canvas.save();
        canvas.translate(w/10,0);
        for(CommandText commandText:commandTexts) {
            commandText.drawText(canvas,paint);
        }
        if(currCommandText!=null) {
            currCommandText.drawText(canvas, paint);
        }
        canvas.restore();
        canvas.restore();
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            InputMethodManager inputMethodManager = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,InputMethodManager.HIDE_IMPLICIT_ONLY);
        }
        return true;
    }
}
