package com.example.birendra.wildlifeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Animals extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animals);
        Intent receiver=getIntent();
        String park = receiver.getStringExtra("park");
        TextView txt=(TextView)findViewById(R.id.textViewAnimals);
        //FIre Query
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        String text=databaseAccess.getAnimalsFound(park);
        txt.setText(text);
        databaseAccess.close();
    }
}
