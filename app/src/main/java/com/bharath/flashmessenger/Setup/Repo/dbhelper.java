package com.bharath.flashmessenger.Setup.Repo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.bharath.flashmessenger.Setup.Model.SetupModel;

import java.util.ArrayList;
import java.util.List;

public class dbhelper extends SQLiteOpenHelper {

    public dbhelper(@Nullable Context context) {
        super(context, "ProfileDetails", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE Profile" + "(" + "ID" +
                "INTEGER PRIMARYKEY," + "NAME" + " TEXT," +"PROFILEPIC"+" TEXT,"+"GENDER"+" TEXT,"+" NUMBER"+" TEXT,"+
                "STATUS"+ " TEXT )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void addtodb(String name, String status, String gender, String phonenum, String profile) {
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("PROFILEPIC", profile);
        values.put("GENDER", gender);
        values.put("NUMBER", phonenum);
        values.put("STATUS", status);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("Profile", null, values);
        db.close();
    }


    public List<SetupModel> showdata(){
        List<SetupModel> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM " +" Profile";
        Cursor cursor = db.rawQuery(select, null);
        if(cursor.moveToFirst()){
            do{
                SetupModel model = new SetupModel();
                model.setName(cursor.getString(1));
                model.setProfile(cursor.getString(2));
                model.setGender(cursor.getString(3));
                model.setPhonenum(cursor.getString(4));
                model.setStatus(cursor.getString(5));
                list.add(model);
            }while(cursor.moveToNext());
        }
        return list;
    }

}
