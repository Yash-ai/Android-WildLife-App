package com.example.birendra.wildlifeapp;

import android.content.Intent;
import android.media.Image;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



import org.w3c.dom.Text;

public class SetLocation extends AppCompatActivity  {
    String park;
    TextView content;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);
        content = (TextView) findViewById(R.id.textViewBestTimeContent);
        Intent receiver = getIntent();
        park = receiver.getStringExtra("park");
        //Fire Query to retrieve city location
        setLocation();
        img=(ImageView)findViewById(R.id.imageLocation);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToMap=new Intent(getApplicationContext(),Map.class);
                goToMap.putExtra("park",park);
                startActivity(goToMap);
            }
        });
    }

    public void setLocation() {
        TextView location = (TextView) findViewById(R.id.textViewLocation);
        //Fire Query for retreival of location column
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        String str=databaseAccess.getLocation(park);
        databaseAccess.close();
        location.setText(str);
    }


}