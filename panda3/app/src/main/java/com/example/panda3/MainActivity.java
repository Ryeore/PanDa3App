package com.example.panda3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureNextButton();
        configureInfoButton();
        configureTasksButton();
        configureNotesButton();
    }

    private void configureNextButton(){
        ImageButton About = (ImageButton) findViewById(R.id.About);
        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            }
        });
    }

    private void configureInfoButton(){
        ImageButton Info = (ImageButton) findViewById(R.id.Info);
        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
            }
        });
    }

    private void configureTasksButton(){
        ImageButton Tasks = (ImageButton) findViewById(R.id.Tasks);
        Tasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TasksActivity.class));
            }
        });
    }

    private void configureNotesButton(){
        ImageButton Notes = (ImageButton) findViewById(R.id.Notes);
        Notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NotesActivity.class));
            }
        });
    }
}
