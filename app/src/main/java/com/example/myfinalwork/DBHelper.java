package com.example.myfinalwork;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.myfinalwork.ui.home.HomeFragment;
import com.example.myfinalwork.ui.login.LoginActivity;

public class DBHelper extends SQLiteOpenHelper {
    public static final String CREATE_INFORMATION="create table INFORMATION("+
            "id integer primary key autoincrement,"+
            "number text NOT NULL UNIQUE,"+
            "name text,"+
            "sex text,"+
            "age text,"+
            "major text,"+
            "shetuan_1 text,"+
            "zhiwei_1 text,"+
            "shetuan_2 text,"+
            "zhiwei_2 text,"+
            "shetuan_3 text,"+
            "zhiwei_3 text)";

    public static final String CREATE_LOGIN="create table LOGIN("+
            "id integer primary key autoincrement,"+
            "number text NOT NULL UNIQUE,"+
            "password text)";
    private Context mContext;
    public DBHelper(Context  context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_INFORMATION);
        db.execSQL(CREATE_LOGIN);
        Toast.makeText(mContext,"成功创建表",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
