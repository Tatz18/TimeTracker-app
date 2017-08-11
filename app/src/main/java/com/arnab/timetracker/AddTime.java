package com.arnab.timetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Arnab on 7/29/2017.
 */

public class AddTime extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_time);
    }

    public void onSave(View view) {
        Intent intent = getIntent();

        EditText timeText = (EditText)findViewById(R.id.add_time);
        intent.putExtra("time",timeText.getText().toString());

        EditText noteText = (EditText)findViewById(R.id.add_notes);
        intent.putExtra("notes",noteText.getText().toString());

        this.setResult(RESULT_OK,intent);
        Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onCancel(View view) {
        //startActivity(new Intent(this,TimeTracker.class));
        Toast.makeText(this,"Cancelled",Toast.LENGTH_SHORT).show();
        finish();
    }
}
