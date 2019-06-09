package com.example.panda3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TimePicker;

public class AddTaskActivity extends AppCompatActivity {

    private static final String TAG = "AddTaskActivity";

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd;
    private EditText editText;
    private TimePicker timePicker1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        editText = (EditText) findViewById(R.id.editText);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        timePicker1 = (TimePicker) findViewById(R.id.timePicker1);

        mDatabaseHelper = new DatabaseHelper(this);

        Intent incomingIntent = getIntent();
        final String date = incomingIntent.getStringExtra("date");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if(!mDatabaseHelper.freeName(editText.getText().toString(),date)){
                    toastMessage("There is task named like this in this day!");
                }
                else if (editText.length() != 0) {
                    AddData(newEntry, date, timePicker1.getHour(), timePicker1.getMinute());
                    editText.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });

    }

    public void AddData(String newEntry, String date, int hour, int minute) {
        boolean insertData = mDatabaseHelper.addData(newEntry, date, hour, minute);

        if (insertData) {
            toastMessage("Data Successfully Inserted!");
        } else {
            toastMessage("There is task with this name");
        }
    }


    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
    public void onBackPressed() {
        Intent intent = new Intent(AddTaskActivity.this, TasksActivity.class);
        startActivity(intent);
        finish();
    }

}
