package com.example.myfinalwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Test_1 extends AppCompatActivity {

    private DBHelper dbHelper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_1);
        //创建个人信息数据库
        dbHelper=new DBHelper(this,"MyFinalWork.db",null,1);
        Button createDatabase=(Button)findViewById(R.id.create);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                db.execSQL("create table INFORMATION("+
                        "id integer primary key autoincrement,"+
                        "number text NOT NULL UNIQUE,"+
                        "name text,"+
                        "sex text,"+
                        "age text,"+
                        "major text,"+
                        "shetuan_1 text)");
                db.execSQL("create table LOGIN("+
                        "id integer primary key autoincrement,"+
                        "number text NOT NULL UNIQUE,"+
                        "password text)");
                db.execSQL("create table JITA_INFORMATION("+
                        "id integer primary key autoincrement,"+
                        "number text NOT NULL UNIQUE,"+
                        "name text)");
                db.execSQL("create table PIANO_INFORMATION("+
                        "id integer primary key autoincrement,"+
                        "number text NOT NULL UNIQUE,"+
                        "name text)");
                db.execSQL("create table DANCE_INFORMATION("+
                        "id integer primary key autoincrement,"+
                        "number text NOT NULL UNIQUE,"+
                        "name text)");
                db.close();
                Toast.makeText(getApplicationContext(), "成功创建表", Toast.LENGTH_LONG).show();

            }
        });

        Button addData=(Button)findViewById(R.id.add_log);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                db.execSQL("insert into LOGIN(number,password) values(?, ?)", new Object[]{"1","123456"});
                db.execSQL("insert into LOGIN(number,password) values(?, ?)", new Object[]{"2","123456"});
                db.execSQL("insert into LOGIN(number,password) values(?, ?)", new Object[]{"3","123456"});
                db.close();
            }
        });

        Button delete_biao=(Button)findViewById(R.id.delete_biao);
        delete_biao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db=dbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                db.execSQL("drop table INFORMATION");
                db.execSQL("drop table LOGIN");
                db.execSQL("drop table JITA_INFORMATION");
                db.execSQL("drop table PIANO_INFORMATION");
                db.execSQL("drop table DANCE_INFORMATION");
                db.close();
            }
        });
        Button querydata=(Button)findViewById(R.id.query);
        querydata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db= dbHelper.getWritableDatabase();
                //查看登录表信息
                Cursor cursor=db.query("LOGIN",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        String number=cursor.getString(cursor.getColumnIndex("number"));
                        String password=cursor.getString(cursor.getColumnIndex("password"));
                        Log.d("SQLiteTest","LOGIN_number is:"+number);
                        Log.d("SQLiteTest","password is:"+password);
                    }while(cursor.moveToNext());
                }
                //看个人信息表信息
                Cursor cursor1=db.query("INFORMATION",null,null,null,null,null,null);
                if(cursor1.moveToFirst()){
                    do{
                        String number=cursor1.getString(cursor1.getColumnIndex("number"));
                        String name=cursor1.getString(cursor1.getColumnIndex("name"));
                        String age=cursor1.getString(cursor1.getColumnIndex("age"));
                        String sex=cursor1.getString(cursor1.getColumnIndex("sex"));
                        String major=cursor1.getString(cursor1.getColumnIndex("major"));
                        Log.d("SQLiteTest","INFORMATION_number is:"+number);
                        Log.d("SQLiteTest","INFORMATION_name is:"+name);
                        Log.d("SQLiteTest","INFORMATION_age is:"+age);
                        Log.d("SQLiteTest","INFORMATION_sex is:"+sex);
                        Log.d("SQLiteTest","INFORMATION_major is:"+major);
                    }while(cursor1.moveToNext());
                }
                //看吉他社成员信息
                Cursor cursor2=db.query("JITA_INFORMATION",null,null,null,null,null,null);
                if(cursor2.moveToFirst()){
                    do{
                        String number=cursor2.getString(cursor2.getColumnIndex("number"));
                        String name=cursor2.getString(cursor2.getColumnIndex("name"));
                        Log.d("SQLiteTest","JITA_INFORMATION_number is:"+number);
                        Log.d("SQLiteTest","JITA_INFORMATION_name is:"+name);
                    }while(cursor2.moveToNext());
                }
                //看钢琴社成员信息
                Cursor cursor3=db.query("PIANO_INFORMATION",null,null,null,null,null,null);
                if(cursor3.moveToFirst()){
                    do{
                        String number=cursor3.getString(cursor3.getColumnIndex("number"));
                        String name=cursor3.getString(cursor3.getColumnIndex("name"));
                        Log.d("SQLiteTest","PIANO_INFORMATION_number is:"+number);
                        Log.d("SQLiteTest","PIANO_INFORMATION_name is:"+name);
                    }while(cursor3.moveToNext());
                }
                //看舞蹈社成员信息
                Cursor cursor4=db.query("DANCE_INFORMATION",null,null,null,null,null,null);
                if(cursor4.moveToFirst()){
                    do{
                        String number=cursor4.getString(cursor4.getColumnIndex("number"));
                        String name=cursor4.getString(cursor4.getColumnIndex("name"));
                        Log.d("SQLiteTest","DANCE_INFORMATION_number is:"+number);
                        Log.d("SQLiteTest","DANCE_INFORMATION_name is:"+name);
                    }while(cursor4.moveToNext());
                }
            }

        });
    }
}