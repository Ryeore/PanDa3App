package com.example.panda3;

import android.content.Intent;
import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.support.v7.widget.Toolbar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AboutActivity extends AppCompatActivity {

    void showToast(String msg) {

        TextView data = (TextView) findViewById(R.id.data);
        TextView work = (TextView) findViewById(R.id.work);
        TextView req = (TextView) findViewById(R.id.req);

        switch(msg){
            case "0":
                data.setVisibility(View.VISIBLE);
                work.setVisibility(View.GONE);
                req.setVisibility(View.GONE);
                break;
            case "1":
                work.setVisibility(View.VISIBLE);
                data.setVisibility(View.GONE);
                req.setVisibility(View.GONE);
                break;
            case "2":
                req.setVisibility(View.VISIBLE);
                data.setVisibility(View.GONE);
                work.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);


        Spinner s1 = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {
                        String pos = Integer.toString(position);
                        showToast(pos);
                    }

                    public void onNothingSelected(AdapterView<?> parent){
                    }
                });

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info:
                startActivity(new Intent(AboutActivity.this, InfoActivity.class));
                finish();
                return true;

            case R.id.action_calendar:
                startActivity(new Intent(AboutActivity.this, TasksActivity.class));
                finish();
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    /*void showText(CharSequence msg) {
        switch (msg){

        }
    }*/
    @Override
    public void onBackPressed() {
        finish();
    }


}