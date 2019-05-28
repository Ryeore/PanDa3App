package com.example.panda3;

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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TasksActivity extends AppCompatActivity {
    private static final  String TAG = "TasksActivity";
    private CalendarView mCalendarView;


    private TextView theDate;

    DatabaseHelper myDB;

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
            }
        });

        //        ListView mlistView = (ListView) findViewById(R.id.listView);
        myDB = new DatabaseHelper(this);

//        ArrayList<String> theList = new ArrayList<>();
//        ArrayList<String> theList1 = new ArrayList<>();
        Cursor data = myDB.getData();
        ArrayList<Map<String,Object>> itemDataList = new ArrayList<Map<String,Object>>();;
        if(data.getCount() == 0){
            Toast.makeText(this, "There are no contents in this list!",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
//                theList.add(data.getString(1));
//                theList1.add(data.getString(3) + ":" + data.getString(4));
                Map<String,Object> listItemMap = new HashMap<String,Object>();
                listItemMap.put("name", data.getString(1));
                listItemMap.put("time", data.getString(3) + ":" + data.getString(4));
                itemDataList.add(listItemMap);
//                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
//                mlistView.setAdapter(listAdapter);
            }
            SimpleAdapter simpleAdapter = new SimpleAdapter(this,itemDataList,android.R.layout.simple_list_item_2,
                    new String[]{"title","description"},new int[]{android.R.id.text1,android.R.id.text2});
            ListView listView = (ListView)findViewById(R.id.listView);
            listView.setAdapter(simpleAdapter);
        }

//        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String name = adapterView.getItemAtPosition(i).toString();
//                Log.d(TAG, "onItemClick: You Clicked on " + name);
//
//                Cursor data = myDB.getItemID(name); //get the id associated with that name
//                int itemID = -1;
//                while(data.moveToNext()){
//                    itemID = data.getInt(0);
//                }
//                if(itemID > -1){
//                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
//                    Intent editScreenIntent = new Intent(NotesActivity.this, EditTaskActivity.class);
//                    editScreenIntent.putExtra("id",itemID);
//                    editScreenIntent.putExtra("name",name);
//                    editScreenIntent.putExtra("date",date);
////                    editScreenIntent.putExtra("hour",hour);
////                    editScreenIntent.putExtra("minute",minute);
//                    startActivity(editScreenIntent);
//                }
//                else{
//                    toastMessage("No ID associated with that name: " + name);
//                }
//            }
//        });


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
