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

import java.util.HashMap;
import java.util.Map;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.Toast;

import android.widget.SimpleAdapter;
import android.util.Log;
import android.widget.AdapterView;

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
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
