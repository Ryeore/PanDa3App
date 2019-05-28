package com.example.panda3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity {

    private static final String TAG = "AddTaskActivity";

    DatabaseHelper mDatabaseHelper;
    private Button btnAdd;
    private EditText editText, editHour, editMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        editText = (EditText) findViewById(R.id.editText);
        editHour = (EditText) findViewById(R.id.edit_hour);
        editMinute = (EditText) findViewById(R.id.edit_minute);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        mDatabaseHelper = new DatabaseHelper(this);

        Intent incomingIntent = getIntent();
        final String date = incomingIntent.getStringExtra("date");

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                int item1 = Integer.parseInt(editHour.getText().toString());
                int item2 = Integer.parseInt(editMinute.getText().toString());

                if (editText.length() != 0) {
                    AddData(newEntry, date, item1, item2);
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
            toastMessage("Something went wrong");
        }
    }


    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
