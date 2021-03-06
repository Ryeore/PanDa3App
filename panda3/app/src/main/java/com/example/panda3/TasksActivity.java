package com.example.panda3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.util.Log;

public class TasksActivity extends AppCompatActivity {
    private static final  String TAG = "TasksActivity";
    private CalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        mCalendarView = (CalendarView) findViewById(R.id.simpleCalendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String date = year + "/" + (month+1) + "/" + dayOfMonth;
                Log.d(TAG, "onSelectDayChange: date: " + date);
                finish();
                Intent intent = new Intent(TasksActivity.this, NotesActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_notes:
                finish();
                startActivity(new Intent(TasksActivity.this, NotesActivity.class));
                return true;

            case R.id.action_calendar:
                finish();
                startActivity(new Intent(TasksActivity.this, TasksActivity.class));
                return true;

            case R.id.action_about:
                finish();
                startActivity(new Intent(TasksActivity.this, AboutActivity.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}
