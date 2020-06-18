package com.example.sqlite_verbindung;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class SQLite_Navigator extends SQLiteAssetHelper {
    public static final String DB_NAME = "kuhlmann.sqlite";
    public static final String table_name = "Boxer";
    public static final String column1 = "ID";
    public static final String column2 = "Vorname";
    public static final String column3 = "Nachname";


    public SQLite_Navigator(Context context) {
        super(context,  DB_NAME, null, 1);
    }

  /*  @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+table_name+"(ID INTEGER PRIMARY KEY AUTOINCREMENT, Vorname Text, Nachname Text)");
    }*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    public boolean insertData(String vname, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cV = new ContentValues();
        cV.put(column2, vname); //mit " testen
        cV.put(column3, name);
        long result = db.insert(table_name,null,cV);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM persdat", null);
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE " +table_name);
        onCreate(db);
    }
}
