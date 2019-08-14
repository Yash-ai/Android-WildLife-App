package com.example.birendra.wildlifeapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class Information extends AppCompatActivity  {

    ViewFlipper viewFlipper;
    TextView history,description,timings,bestTimes;
    ImageView img1,img2,img3,img4,img5;
    Button bestTime,animals,ticket,hotels,timing,activity;
    LinearLayout l1;
    String park;
    LinearLayout l;
    Intent receiver;
    static int c1=0,c2=0;
    DatabaseAccess databaseAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        img1=(ImageView) findViewById(R.id.imageOne);
        img2=(ImageView) findViewById(R.id.imageTwo);
        img3=(ImageView) findViewById(R.id.imageThree);
        img4=(ImageView) findViewById(R.id.imageFour);
        img5=(ImageView)findViewById(R.id.imageFive);
        history=(TextView)findViewById(R.id.textViewHistory);
        description=(TextView)findViewById(R.id.textViewDescription);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        bestTime=(Button)findViewById(R.id.buttonBestTime);
        animals=(Button)findViewById(R.id.buttonAnimals);
        hotels=(Button)findViewById(R.id.buttonHotels);
        ticket=(Button)findViewById(R.id.buttonTicketBooking);
        timing=(Button)findViewById(R.id.buttonTimings);
        activity=(Button)findViewById(R.id.buttonActivities);
        timings=(TextView)findViewById(R.id.textViewTimings);
        bestTimes=(TextView)findViewById(R.id.textViewBestTimes);
        l=(LinearLayout)findViewById(R.id.linearLayoutVisibility);

        receiver=getIntent();
        park=receiver.getStringExtra("park");
        databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        flip();
        setText();

        timing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c1++;
                setTiming();
            }
        });

        bestTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c2++;
                setBestTime();
            }
        });

    }
    public void flip() //Flipping Image to be changed
    {
        img1.setImageBitmap(databaseAccess.getImage1(park));
        img2.setImageBitmap(databaseAccess.getImage2(park));
        img3.setImageBitmap(databaseAccess.getImage3(park));
        img4.setImageBitmap(databaseAccess.getImage4(park));
        img5.setImageBitmap(databaseAccess.getImage5(park));
        viewFlipper.setAutoStart(true);
        viewFlipper.setFlipInterval(2000);
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        viewFlipper.setInAnimation(in);
        viewFlipper.setOutAnimation(out);
    }


    public void setText()
    {
       //FIre Query for History
        databaseAccess.open();
        String his=databaseAccess.getHistory(park);
        databaseAccess.close();
        history.setText(his);
        databaseAccess.open();
        String desc=databaseAccess.getDescription(park);
        databaseAccess.close();
        description.setText(desc);
        ellipsize(history);
        ellipsize(description);
    }
    public void ellipsize(final TextView txt)  //Ellipsizing Text
   {
        String inputText=txt.getText().toString();
       if(inputText.length()>150)
       {
           String text=inputText.substring(0,150)+"...";
           final String fulltext=inputText;

           final SpannableString ss = new SpannableString(text+"View More");

           ClickableSpan span1 = new ClickableSpan() {
               @Override
               public  void onClick(View textView) {
                   // do some thing
                   SpannableString ss1 = new SpannableString(fulltext+"Show Less");
                   ClickableSpan span2 = new ClickableSpan() {
                       @Override
                       public  void onClick(View textView) {
                           // do some thing
                           txt.setText(ss);
                           txt.setMovementMethod(LinkMovementMethod.getInstance());

                       }
                   };
                   ss1.setSpan(span2, fulltext.length(), ss1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                   ss1.setSpan(new ForegroundColorSpan(Color.RED), fulltext.length(), ss1.length(),
                           Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


                   txt.setText(ss1);
                   txt.setMovementMethod(LinkMovementMethod.getInstance());
               }
           };
          ss.setSpan(span1, 153, 162, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
          ss.setSpan(new ForegroundColorSpan(Color.RED), 153,162,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

           txt.setText(ss);
           txt.setMovementMethod(LinkMovementMethod.getInstance());
       }
       else
       {
           txt.setText(inputText);
       }
    }


    public void setActivities(View view)
    {
        Intent goToBestTime=new Intent().setClass(getApplicationContext(),BestTime.class);
        goToBestTime.putExtra("park",park);
        startActivity(goToBestTime);
    }
    public void setAnimals(View view)
    {
        Intent goToAnimals=new Intent().setClass(getApplicationContext(),Animals.class);
        goToAnimals.putExtra("park",park);
        startActivity(goToAnimals);
    }


    public void setTiming()
    {
                if(c1%2!=0)
                {
                    //Fire query for timing column
                    databaseAccess.open();
                    String str=databaseAccess.getTimings(park);
                    databaseAccess.close();
                    timings.setText(str);
                    l.setVisibility(View.VISIBLE);
                    timings.setVisibility(View.VISIBLE);
                }
                if(c1%2==0)
                {
                    timings.setVisibility(View.INVISIBLE);
                    if(c2%2==0)
                    {
                        l.setVisibility(View.GONE);
                    }
                }

    }
    public void setBestTime()
    {

        if(c2%2!=0)
        {
            //Fire query for activities column
            databaseAccess.open();
            String str=databaseAccess.getBestTimeToVisit(park);
            databaseAccess.close();
            bestTimes.setText(str);

            l.setVisibility(View.VISIBLE);
            bestTimes.setVisibility(View.VISIBLE);
        }
        if(c2%2==0)
        {
            bestTimes.setVisibility(View.INVISIBLE);
            if(c1%2==0)
            {
                l.setVisibility(View.GONE);
            }

        }

    }


    public void setHotels(View view)
    {
        Intent goToSetText=new Intent().setClass(getApplicationContext(),SetText.class);
        goToSetText.putExtra("park",park);
        goToSetText.putExtra("button","hotels");
        startActivity(goToSetText);
    }
    public void setTicketBooking(View view)
    {
        databaseAccess.open();
        String url=databaseAccess.getURL(park);
        Intent viewIntent=new Intent("android.intent.action.VIEW",Uri.parse(url));
        startActivity(viewIntent);
    }


    public void setDND(View view)
    {
        Intent goToSetText=new Intent().setClass(getApplicationContext(),SetText.class);
        goToSetText.putExtra("park",park);
        goToSetText.putExtra("button","DND");
        startActivity(goToSetText);
    }
    public void setContactUs(View view)
    {
        Intent goToSetText=new Intent().setClass(getApplicationContext(),SetText.class);
        goToSetText.putExtra("park",park);
        goToSetText.putExtra("button","ContactUs");
        startActivity(goToSetText);
    }

    public void setLocation(View view)
    {
        Intent goToSetLocation=new Intent().setClass(getApplicationContext(),SetLocation.class);
        goToSetLocation.putExtra("park",park);
        startActivity(goToSetLocation);
    }



}
