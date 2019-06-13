package com.example.panda3;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
<<<<<<< HEAD
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TimePicker;
=======
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TimePicker;

>>>>>>> 5d78ad927607a70e98ded51e2152d452f185d2ee
/**
 * Created by User on 2/28/2017.
 */

public class EditTaskActivity extends AppCompatActivity {

    private static final String TAG = "EditDataActivity";

    private ImageButton btnSave,btnDelete;
    private EditText editable_item, hour_item, minute_item;
    private TextView taskname;

    DatabaseHelper mDatabaseHelper;

    private String selectedName, selectedDate;
    private int selectedID, selectedHour, selectedMinute;
    private TimePicker timePicker1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edittask);
        btnSave = (ImageButton) findViewById(R.id.btnSave);
        btnDelete = (ImageButton) findViewById(R.id.btnDelete);
        editable_item = (EditText) findViewById(R.id.editable_item);
        taskname = (TextView) findViewById(R.id.task) ;
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);
        mDatabaseHelper = new DatabaseHelper(this);

        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getIntExtra("id",-1); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");

        //set the text to show the current selected name
        taskname.setText(selectedName);
        editable_item.setText(selectedName);

        //now get the hour we passed as an extra
        selectedDate = receivedIntent.getStringExtra("date");

        //now get the hour we passed as an extra
        selectedHour = receivedIntent.getIntExtra("hour",-1);

        //set the text to show the current selected hour
        timePicker1.setHour(selectedHour);

        //now get the minute we passed as an extra
        selectedMinute = receivedIntent.getIntExtra("minute",-1);

        //set the text to show the current selected minute
        timePicker1.setMinute(selectedMinute);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = editable_item.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.updateTask(item,selectedID,selectedName, selectedHour, timePicker1.getHour(), selectedMinute, timePicker1.getMinute());
                }else{
                    toastMessage("You must enter a name!");
                }
                Intent intent = new Intent(EditTaskActivity.this, TasksActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                mDatabaseHelper.deleteTask(selectedID, selectedName, selectedDate);
                editable_item.setText("");
                toastMessage("removed from database");}
                catch (Exception e) {toastMessage("something went wrong");
                }
                Intent intent = new Intent(EditTaskActivity.this, TasksActivity.class);
                startActivity(intent);
                finish();
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
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EditTaskActivity.this, TasksActivity.class);
        startActivity(intent);
        finish();
    }
}
