package com.example.birendra.wildlifeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BestTime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_time);
        TextView content=(TextView)findViewById(R.id.textViewBestTimeContent);
        Intent receiver=getIntent();
        String park = receiver.getStringExtra("park");
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        //Fire Query here
        String str=databaseAccess.getActivities(park);
        content.setText(str);
    }
}
