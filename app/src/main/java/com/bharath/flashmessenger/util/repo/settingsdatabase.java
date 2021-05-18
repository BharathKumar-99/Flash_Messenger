package com.bharath.flashmessenger.util.repo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;

import com.bharath.flashmessenger.Setup.Model.SetupModel;

import java.util.ArrayList;
import java.util.List;

public class settingsdatabase extends SQLiteOpenHelper { private static final String TAG = "qwe" ;

    public settingsdatabase(@Nullable Context context) {
        super(context, "Settings", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE Settings" + "(" + "ID" +
                "INTEGER PRIMARYKEY," + "Wallpaper" + " TEXT )";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addtodb( Uri Wallpaper) {
        ContentValues values = new ContentValues();
        values.put("Wallpaper", String.valueOf(Wallpaper));
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("Settings", null, values);
        db.close();
    }


    public Uri showdata(){

        Uri wallpaper = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM " +" Settings";
        Cursor cursor = db.rawQuery(select, null);
        if(cursor.moveToFirst()){
            do{
                wallpaper= Uri.parse(cursor.getString(1));
                Log.d("ssj", "showdata: "+ wallpaper);
            }while(cursor.moveToNext());
        }
        return wallpaper;
    }


    public void updateItem(Uri item) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Wallpaper", String.valueOf(item));
        db.update("Settings", contentValues,null,null);

    }

}
