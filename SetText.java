package com.example.birendra.wildlifeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SetText extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_text);
        TextView content=(TextView)findViewById(R.id.textViewSetText);
        Intent receiver=getIntent();
        String park = receiver.getStringExtra("park");
        String button=receiver.getStringExtra("button");
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        switch(button)

        {
            case "hotels" :  String hotel=databaseAccess.getHotels(park);
                             content.setText(hotel);
                             break;
            case "DND" :   String DND=databaseAccess.getDND(park);
                            content.setText(DND);
                            break;
            case "ContactUs" :   String contact=databaseAccess.getContactUs(park);
                                content.setText(contact);
                                 break;
        }
        databaseAccess.close();
        }
}
