package com.arnab.timetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

/**
 * Created by Arnab on 7/29/2017.
 */

public class TimeTracker extends AppCompatActivity {
    private TimeTrackerAdapter timeTrackerAdapter;
    private TimeTrackerDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new TimeTrackerDatabaseHelper(this);

        ListView listView = (ListView) findViewById(R.id.times_list);
        timeTrackerAdapter = new TimeTrackerAdapter(this,databaseHelper.getTimeRecordList());
        listView.setAdapter(timeTrackerAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.time_list_menu,menu);
        return true;
    }

    public static int TIME_ENTRY_REQUEST_CODE=1;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add_time_menu_item)
        {
            Intent intent = new Intent("com.arnab.timetracker.Addtime");
            startActivityForResult(intent,TIME_ENTRY_REQUEST_CODE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == TIME_ENTRY_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                String notes = data.getStringExtra("notes");
                String time = data.getStringExtra("time");

                databaseHelper.saveTimeRecord(time,notes);


            }
        }
    }
}
