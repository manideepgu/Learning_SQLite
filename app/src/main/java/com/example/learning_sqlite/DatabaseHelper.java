package com.example.learning_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String database_name="TestLog.db";
    public static String table_name="communicationlog";
    public static String COL_1="ID";
    public static String COL_2="Logs";
    public static String COL_3="Time";

    public DatabaseHelper(Context context) {

        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + table_name +" (ID INTEGER PRIMARY KEY AUTOINCREMENT ,Logs " +
                "TEXT,Time INTEGER) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table_name);
        onCreate(db);
    }

    public boolean insertData(String Logs,String Time){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put(COL_2,Logs);
        content.put(COL_3,Time);
        long success = db.insert(table_name,null,content);
        if(success==-1){
            return false;
        }
        else{
             return true;

        }
    }

    public Cursor viewdata(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cur = db.rawQuery("select * from "+table_name,null);
        return cur;
    }

    public boolean update(String ID,String Logs,String Time){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put(COL_1,ID);
        content.put(COL_2,Logs);
        content.put(COL_3,Time);
        db.update(table_name,content,"ID=?",new String[]{ID});
        return true;
    }
    public int delete(String ID){
        SQLiteDatabase db=this.getWritableDatabase();

        return db.delete(table_name,"ID=?",new String[]{ID});
    }


}
