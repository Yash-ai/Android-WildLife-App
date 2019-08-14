package com.example.birendra.wildlifeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListOfParks extends AppCompatActivity {
    String incomingState = null, incomingAnimal = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_parks);
        Intent caller = getIntent();
        incomingState = caller.getStringExtra("state");
        incomingAnimal = caller.getStringExtra("animal");
        ListView parklist = (ListView) findViewById(R.id.listViewParks);
        ArrayList<String> lps = null;

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        //Fire Query
        if (incomingState != null) {
            lps = databaseAccess.getParkbyState(incomingState);
            databaseAccess.close();
        }
        if (incomingAnimal != null) {
            lps = databaseAccess.getParkbyAnimal(incomingAnimal);
            databaseAccess.close();
        }
            ArrayAdapter<String> aradpt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lps);

            parklist.setAdapter(aradpt);
            parklist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent goToInfo = new Intent().setClass(getApplicationContext(), Information.class);
                    String str = adapterView.getItemAtPosition(i).toString();
                    goToInfo.putExtra("park", str);
                    startActivity(goToInfo);

                }
            });


        }
}
