package com.example.panda3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        
        progressAnimation();


    }

    public void progressAnimation(){
        ProgressBarAnimation anim = new ProgressBarAnimation(this,progressBar, textView, 0f, 100f);
        anim.setDuration(SPLASH_TIME_OUT);
        progressBar.setAnimation(anim);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

}