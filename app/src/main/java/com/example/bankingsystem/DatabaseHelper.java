package com.example.bankingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(190,'Siya',100.00,'saiya@gmail.com','XXXXXXXXXXXX1224','Siya59')");
        db.execSQL("insert into user_table values(281,'neha',900.00,'neha@gmail.com','XXXXXXXXXXXX2331','Neha38')");
        db.execSQL("insert into user_table values(392,'riya',80.0,'riya@gmail.com','XXXXXXXXXXXX3442','Riya345')");
        db.execSQL("insert into user_table values(453,'Priya',150.1,'priya@gmail.com','XXXXXXXXXXXX4113','Priya145')");
        db.execSQL("insert into user_table values(534,'rohini',203.8,'rohini@gmail.com','XXXXXXXXXXXX2335','Rohini25')");
        db.execSQL("insert into user_table values(345,'Anu',100.16,'anu@gmail.com','XXXXXXXXXXXX3552','Anu125')");
        db.execSQL("insert into user_table values(736,'rutuja',600.0,'rutuja@gmail.com','XXXXXXXXXXXX4553','Rutuja13')");
        db.execSQL("insert into user_table values(847,'ashwati',750.2,'ashwati@gmail.com','ABDUL1334','Ashwati9876')");
        db.execSQL("insert into user_table values(908,'ritika',498.6,'ritika@gmail.com','XXXXXXXXXXXX3446','Ritika45')");
        db.execSQL("insert into user_table values(129,'priyanka',273.9,'priyanka@gmail.com','XXXXXXXXXXXX4553','Priya123')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
