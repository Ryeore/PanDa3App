package com.example.panda3;

import java.util.Calendar;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.widget.AdapterView;

import android.widget.CursorAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TasksActivity extends AppCompatActivity {
    private static final  String TAG = "TasksActivity";
    private CalendarView mCalendarView;
    private ImageButton btnAddTask;
    private TextView theDate;

    public class Date {
        public String clicked;
    }
    Date calendarDate = new Date();
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        btnAddTask = (ImageButton) findViewById(R.id.btnAddTask);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        mCalendarView = (CalendarView) findViewById(R.id.simpleCalendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String date = year + "/" + (month+1) + "/" + dayOfMonth;

                calendarDate.clicked=date;

                Log.d(TAG, "onSelectDayChange date: " + date);

                myDB = new DatabaseHelper(getApplicationContext());
                Cursor data = myDB.getData();

                ArrayList<Map<String,Object>> itemDataList = new ArrayList<Map<String,Object>>();;
                if(data.getCount() == 0){
                    Toast.makeText(getApplicationContext(), "There are no contents in this list!",Toast.LENGTH_LONG).show();
                }else{
                    while(data.moveToNext()){
                        Log.d(TAG, "onSelectDayChange date comparison: " + date + " database: " + data.getString(2) + " result " + (data.getString(2).equals(date)));
                        if(data.getString(2).equals(date)){
                            Map<String,Object> listItemMap = new HashMap<String,Object>();
                            listItemMap.put("name", data.getString(1));
                            listItemMap.put("time", data.getString(3) + ":" + data.getString(4));
                            itemDataList.add(listItemMap);}
                    }
                    SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(),itemDataList,android.R.layout.simple_list_item_2,
                            new String[]{"name","time"},new int[]{android.R.id.text1,android.R.id.text2});
                    ListView listView = (ListView)findViewById(R.id.listView);
                    listView.setAdapter(simpleAdapter);
                }
            }

        });
        Calendar c = Calendar.getInstance();
        calendarDate.clicked = c.get(Calendar.YEAR) + "/" + (c.get(Calendar.MONTH)+1) + "/" + c.get(Calendar.DAY_OF_MONTH);
        myDB = new DatabaseHelper(getApplicationContext());
        Cursor data = myDB.getData();
        ListView listView = (ListView)findViewById(R.id.listView);
        final ArrayList<Map<String,Object>> itemDataList = new ArrayList<Map<String,Object>>();
        if(data.getCount() == 0){
            Toast.makeText(getApplicationContext(), "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                if(data.getString(2).equals(calendarDate.clicked)){
                    Map<String,Object> listItemMap = new HashMap<String,Object>();
                    listItemMap.put("name", data.getString(1));
                    listItemMap.put("time", data.getString(3) + ":" + data.getString(4));
                    itemDataList.add(listItemMap);}
            }
            SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(),itemDataList,android.R.layout.simple_list_item_2,
                    new String[]{"name","time"},new int[]{android.R.id.text1,android.R.id.text2});

            listView.setAdapter(simpleAdapter);
        }

        btnAddTask.setOnClickListener(new View.OnClickListener(){
                                          @Override
                                          public void onClick(View view){
                                              Intent intent = new Intent(TasksActivity.this, AddTaskActivity.class);
                                              intent.putExtra("date", calendarDate.clicked);
                                              startActivity(intent);
                                          }
                                      }
        );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                myDB = new DatabaseHelper(getApplicationContext());
                String object = adapterView.getItemAtPosition(index).toString();
                String[] fullname = object.split(", ");
                String[] name = fullname[0].split("=");
                Log.d(TAG, "onItemClick: You Clicked on " + name[1]);
                Cursor data = myDB.getData();
                int ID = -1, min=0, hour=0;
                while(data.moveToNext()){
                    if(data.getString(1).equals(name[1])){
                        ID=data.getInt(0);
                        min=data.getInt(4);
                        hour=data.getInt(3);
                        break;
                    }
                }
                try{
//                    Cursor data = myDB.getItemID(name[1]); //get the id associated with that name
                    Log.d(TAG, "onItemClick: database ID " + ID);
                    if(ID>-1){
                        Log.d(TAG, "onItemClick: The ID is: " + ID);
                        Intent editScreenIntent = new Intent(TasksActivity.this, EditTaskActivity.class);
                        editScreenIntent.putExtra("id",ID);
                        editScreenIntent.putExtra("name",name[1]);
                        editScreenIntent.putExtra("date",calendarDate.clicked);
                        editScreenIntent.putExtra("hour",hour);
                        editScreenIntent.putExtra("minute",min);
                        startActivity(editScreenIntent);}}
                catch (Exception e){Log.d(TAG, "onItemClick: something went wrong");}


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
                startActivity(new Intent(TasksActivity.this, NotesActivity.class));
                return true;

            case R.id.action_about:
                startActivity(new Intent(TasksActivity.this, AboutActivity.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }




}
