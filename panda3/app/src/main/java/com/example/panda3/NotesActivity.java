package com.example.panda3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;

import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {

    private static final String TAG = "NotesActivity";

    private TextView theDate;
    private Button btnGoToTasks, btnAddTask;

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        ListView listView = (ListView) findViewById(R.id.listView);
        myDB = new DatabaseHelper(this);

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getData();
        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                theList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }
        }


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        theDate = (TextView) findViewById(R.id.date);
        btnGoToTasks = (Button) findViewById(R.id.btnGoToTasks);
        btnAddTask = (Button) findViewById(R.id.btnAddTask);

        Intent incomingIntent = getIntent();
        final String date = incomingIntent.getStringExtra("date");
        theDate.setText(date);

        btnGoToTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                finish();
                Intent intent = new Intent(NotesActivity.this, TasksActivity.class);
                startActivity(intent);
            }
                                        }
        );
        btnAddTask.setOnClickListener(new View.OnClickListener(){
                                            @Override
                                            public void onClick(View view){
                                                Intent intent = new Intent(NotesActivity.this, AddTaskActivity.class);
                                                intent.putExtra("date", date);
                                                startActivity(intent);
                                            }
                                        }
        );

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_notes:
                finish();
                startActivity(new Intent(NotesActivity.this, NotesActivity.class));
                return true;

            case R.id.action_calendar:
                finish();
                startActivity(new Intent(NotesActivity.this, TasksActivity.class));
                return true;

            case R.id.action_about:
                finish();
                startActivity(new Intent(NotesActivity.this, AboutActivity.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

/*    private void configureBackButton() {
        Button Back = (Button) findViewById(R.id.Back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }*/
}
