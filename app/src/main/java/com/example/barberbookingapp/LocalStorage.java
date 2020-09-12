package com.example.barberbookingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;

public class LocalStorage extends SQLiteOpenHelper {
    public static final String databaseName = "myDatabase.db";


    public static final String userName = "userName";
    public static final String pass = "pass";
    public static final String loginStyle = "loginStyle";


    static SQLiteDatabase db;


    public LocalStorage(Context context)
    {
        super(context, databaseName, null, 1);

         db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL("create table localData (userName TEXT PRIMARY KEY ,pass TEXT ,loginStyle TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS localData");

    }

    public boolean InsertAccount(String userName, String pass, String loginStyle)
    {

        ContentValues values = new ContentValues();
        values.put(this.userName,userName);
        values.put(this.pass,pass);
        values.put(this.loginStyle,loginStyle);

    long result = db.insert("localData",null,values);

    if (result==-1)
    {
        return false;
    }
    else
    {
        return  true;
    }

    }
    public Cursor getAll()
    {
        Cursor result = db.rawQuery("select * FROM localData",null);
        return result;
    }

    public boolean deleteAll(String userName)
    {
        if(db.delete("localData","userName=?",new String[]{userName})>0) return true;
        return false;
    }
}
