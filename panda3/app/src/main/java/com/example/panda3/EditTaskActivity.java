package com.example.panda3;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by User on 2/28/2017.
 */

public class EditTaskActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private Button btnSave,btnDelete;
    private EditText editable_item, hour_item, minute_item;

    DatabaseHelper mDatabaseHelper;

    private String selectedName, selectedDate;
    private int selectedID, selectedHour, selectedMinute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittask);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        editable_item = (EditText) findViewById(R.id.editable_item);
        hour_item = (EditText) findViewById(R.id.edit_hour);
        minute_item = (EditText) findViewById(R.id.edit_minute) ;
        mDatabaseHelper = new DatabaseHelper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");

        //set the text to show the current selected name
        editable_item.setText(selectedName);

        //now get the hour we passed as an extra
        selectedDate = receivedIntent.getStringExtra("date");

        //now get the hour we passed as an extra
        selectedHour = receivedIntent.getIntExtra("hour",-1);

        //set the text to show the current selected hour
        hour_item.setText(selectedHour);

        //now get the minute we passed as an extra
        selectedMinute = receivedIntent.getIntExtra("minute",-1);

        //set the text to show the current selected minute
        minute_item.setText(selectedMinute);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                int item1 = Integer.parseInt(hour_item.getText().toString());
                int item2 = Integer.parseInt(minute_item.getText().toString());
                if(!item.equals("")){
                    mDatabaseHelper.updateTask(item,selectedID,selectedName, selectedHour, item1, selectedMinute, item2);
                }else{
                    toastMessage("You must enter a name");
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabaseHelper.deleteTask(selectedID,selectedName, selectedDate, selectedHour, selectedMinute);
                editable_item.setText("");
                toastMessage("removed from database");
            }
        });

    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
