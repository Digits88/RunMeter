package com.example.hafiz.runningmeter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hafiz on 7/3/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="contacts.db";
    private static final String TABLE_NAME="contacts";
    private static final String COL_ID="id";
    private static final String COL_USERNAME="username";
    private static final String COL_PASSWORD="password";
    private static final String COL_HEIGHT="height";
    private static final String COL_WEIGHT="weight";
    private static final String COL_AGE="age";
    private static final String COL_SEX="sex";
    SQLiteDatabase db;
    private static final String TABLE_CREATE="create table contacts(id integer primary key not null ,"+
            "username text not null,password text not null,height text not null,weight text not null,age text not null,sex text not null);";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;
    }
    public void insertContact(Contact c){
        db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query="select * from contacts";
        Cursor cursor = db.rawQuery(query,null);
        int count=cursor.getCount();
        values.put(COL_ID,count);
        values.put(COL_USERNAME,c.getUsername());
        values.put(COL_PASSWORD,c.getPassword());
        values.put(COL_HEIGHT,c.getHeight());
        values.put(COL_WEIGHT,c.getWeight());
        values.put(COL_AGE,c.getAge());
        values.put(COL_SEX,c.getSex());
        db.insert(TABLE_NAME,null,values);
        db.close();

    }
    public String searchPass(String uname){
        db=this.getWritableDatabase();
        String query = "select username,password from contacts";
        Cursor cursor = db.rawQuery(query,null);
        String a,b;
        b="not found";
        if(cursor.moveToFirst()){
            do{
                a=cursor.getString(0);

                if(a.equals(uname)){
                    b=cursor.getString(1);
                    break;
                }
            }while (cursor.moveToNext());
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String query="DROP TABLE IS EXISTS"+TABLE_NAME;
            db.execSQL(query);
            this.onCreate(db);
    }
}
