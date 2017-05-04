package com.anwesome.ui.terminalview;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by anweshmishra on 04/05/17.
 */
public class Terminal {
    private Activity activity;
    public Terminal(Activity activity) {
        this.activity = activity;
        initKeyborard();
    }
    private void initKeyborard() {

    }
    public void show() {
        TerminalView terminalView = new TerminalView(activity);
        activity.setContentView(terminalView);
    }
}
