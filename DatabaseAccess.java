package com.example.birendra.wildlifeapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c=null;

    private DatabaseAccess(Context context)
    {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open()
    {
        this.db = openHelper.getWritableDatabase();
    }


    public void close()
    {
        if (db != null) {
            this.db.close();
        }
    }
    public ArrayList<String> getParks()
    {
       SQLiteDatabase db1=openHelper.getReadableDatabase();
        // SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/data/com.package.name/databases/database.db", null, SQLiteDatabase.OPEN_READWRITE);
        Cursor ct=db1.rawQuery("select parkname from nationalparks",null);
        ArrayList<String> list=new ArrayList<String>();
        while(ct.moveToNext())
        {
            list.add(ct.getString(0));
        }
        return list;
    }
    public ArrayList<String> getParkbyState(String s)
    {
        c=db.rawQuery("select parkname from nationalparks where state='"+s+"'",new String[]{});
        ArrayList<String> list=new ArrayList<String>();
        while(c.moveToNext())
        {
            list.add(c.getString(0));
        }
        return list;
    }
    public ArrayList<String> getParkbyAnimal(String s)
    {
        c=db.rawQuery("select parkname from nationalparks where animalsfound like'%"+s+"%'",new String[]{});
        ArrayList<String> list=new ArrayList<String>();
        while(c.moveToNext())
        {
            list.add(c.getString(0));
        }
        return list;
    }
    public String getHistory(String s)
    {
        c=db.rawQuery("select history from nationalparks where parkname='"+s+"'",new String[]{});
        String str=null;
        if(c.moveToFirst())
        {
            str=c.getString(0);
        }
        return  str;
    }
    public String getDescription(String s)
    {
        c=db.rawQuery("select description from nationalparks where parkname='"+s+"'",new String[]{});
        String str=null;
        if(c.moveToFirst())
        {
            str=c.getString(0);
        }
        return  str;
    }
    public String getActivities(String s)
    {
        c=db.rawQuery("select activities from nationalparks where parkname='"+s+"'",new String[]{});
        String str=null;
        if(c.moveToFirst())
        {
            str=c.getString(0);
        }
        return  str;
    }
    public String getAnimalsFound(String s)
    {
        c=db.rawQuery("select animalsfound from nationalparks where parkname='"+s+"'",new String[]{});
        String str=null;
        if(c.moveToFirst())
        {
            str=c.getString(0);
        }
        return  str;
    }
    public String getTimings(String s)
    {
        c=db.rawQuery("select timings from nationalparks where parkname='"+s+"'",new String[]{});
        String str=null;
        if(c.moveToFirst())
        {
            str=c.getString(0);
        }
        return  str;
    }
    public String getBestTimeToVisit(String s)
    {
        c=db.rawQuery("select besttimetovisit from nationalparks where parkname='"+s+"'",new String[]{});
        String str=null;
        if(c.moveToFirst())
        {
            str=c.getString(0);
        }
        return  str;
    }
    public String getHotels(String s)
    {
        c=db.rawQuery("select hotelsnearby from nationalparks where parkname='"+s+"'",new String[]{});
        String str=null;
        if(c.moveToFirst())
        {
            str=c.getString(0);
        }
        return  str;
    }
    public String getDND(String s)
    {
        c=db.rawQuery("select dosanddonts from nationalparks where parkname='"+s+"'",new String[]{});
        String str=null;
        if(c.moveToFirst())
        {
            str=c.getString(0);
        }
        return  str;
    }
    public String getContactUs(String s)
    {
        c=db.rawQuery("select contactus from nationalparks where parkname='"+s+"'",new String[]{});
        String str=null;
        if(c.moveToFirst())
        {
            str=c.getString(0);
        }
        return  str;
    }
    public String getLocation(String s)
    {
        c=db.rawQuery("select location from nationalparks where parkname='"+s+"'",new String[]{});
        String str=null;
        if(c.moveToFirst())
        {
            str=c.getString(0);
        }
        return  str;
    }
    public Double getLat(String s)
    {
        c=db.rawQuery("select latitude from nationalparks where parkname='"+s+"'",new String[]{});
        Double lat=0.0;
        if(c.moveToFirst())
        {
            lat=c.getDouble(0);
        }
        return  lat;
    }
    public Double getLang(String s)
    {
        c=db.rawQuery("select longitude from nationalparks where parkname='"+s+"'",new String[]{});
        Double lang=0.0;
        if(c.moveToFirst())
        {
            lang=c.getDouble(0);
        }
        return  lang;
    }
    public String getURL(String s)
    {
        c=db.rawQuery("select ticketbookings from nationalparks where parkname='"+s+"'",new String[]{});
        String str=null;
        if(c.moveToFirst())
        {
            str=c.getString(0);
        }
        return  str;
    }
    public Bitmap getImage1(String s)
    {
        Bitmap images=null;
        c=db.rawQuery("select image1 from nationalparks where parkname='"+s+"'",new String[]{});
        if(c.moveToNext())
        {
            byte img[]=c.getBlob(0);
            images=BitmapFactory.decodeByteArray(img,0,img.length);

        }
        return images;
    }
    public Bitmap getImage2(String s)
    {
        Bitmap images=null;
        c=db.rawQuery("select image2 from nationalparks where parkname='"+s+"'",new String[]{});
        if(c.moveToNext())
        {
            byte img[]=c.getBlob(0);
            images=BitmapFactory.decodeByteArray(img,0,img.length);

        }
        return images;
    }
    public Bitmap getImage3(String s)
    {
        Bitmap images=null;
        c=db.rawQuery("select image3 from nationalparks where parkname='"+s+"'",new String[]{});
        if(c.moveToNext())
        {
            byte img[]=c.getBlob(0);
            images=BitmapFactory.decodeByteArray(img,0,img.length);

        }
        return images;
    }
    public Bitmap getImage4(String s)
    {
        Bitmap images=null;
        c=db.rawQuery("select image4 from nationalparks where parkname='"+s+"'",new String[]{});
        if(c.moveToNext())
        {
            byte img[]=c.getBlob(0);
            images=BitmapFactory.decodeByteArray(img,0,img.length);

        }
        return images;
    }
    public Bitmap getImage5(String s)
    {
        Bitmap images=null;
        c=db.rawQuery("select image5 from nationalparks where parkname='"+s+"'",new String[]{});
        if(c.moveToNext())
        {
            byte img[]=c.getBlob(0);
            images=BitmapFactory.decodeByteArray(img,0,img.length);

        }
        return images;
    }

}
