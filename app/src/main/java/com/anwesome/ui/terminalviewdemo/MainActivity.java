package com.anwesome.ui.terminalviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anwesome.ui.terminalview.TerminalView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TerminalView terminalView = new TerminalView(this);
        setContentView(terminalView);
    }
}
