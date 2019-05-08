package com.example.panda3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        configureNextButton();
        configureInfoButton();
        configureTasksButton();
        configureNotesButton();


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_notes:
                startActivity(new Intent(MainActivity.this, NotesActivity.class));
                return true;

            case R.id.action_calendar:
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
                return true;

            case R.id.action_tasks:
                startActivity(new Intent(MainActivity.this, TasksActivity.class));
                return true;

            case R.id.action_about:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
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
