package com.example.lab07;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper
{
    public DB (@Nullable Context context,@Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context,name,factory,version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table my_test (my_key TEXT PRIMARY key, my_value TEXT);";
                db.execSQL(sql);
    }
    public void do_insert (String key,String value)
    {
        String sql = "INSERT INTO my_test VALUES ('"+key+"','"+value+"');";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);

    }
    public  String do_select (String key)
    {
        String sql= "Select my_value From my_test where my_key='"+key+"';";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery(sql,null);
        if(cur.moveToFirst()==true) {
            return cur.getString(0);
        }
        return "(!) not found";
    }
    public boolean check (String key)
    {
        String sql= "Select my_value From my_test where my_key='"+key+"';";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.rawQuery(sql,null);
        if(cur.moveToFirst()==true) {
            return false;
        }
        return true;


    }
    public void do_upgrade(String key,String value )
    {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Update my_test  set my_value = '"+value+"'Where my_key='"+key+"';";
        db.execSQL(sql);
    }
    public  void do_delete (String key)
    {
        SQLiteDatabase db = getReadableDatabase();
        String sql ="delete from my_test Where my_key='"+key+"';";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
