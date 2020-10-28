package com.example.myfinalwork.Piano;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.myfinalwork.DBHelper;
import com.example.myfinalwork.R;
import com.example.myfinalwork.jitashe.Jita_guanzhu;
import com.example.myfinalwork.jitashe.Jitashe;

import java.util.HashMap;

import static com.example.myfinalwork.ui.login.LoginActivity.xuehao;

public class GuanzhuOrNot_Piano extends AppCompatActivity {

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano);
        dbHelper = new DBHelper(this, "MyFinalWork.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
//在JITA_INFORMATION里查找是否存在本用户，如果有则跳转JITA_GUANZHU页面，如果无跳转至JITASHE页面
        Cursor cursor1 = db.query("PIANO_INFORMATION", null, null, null, null, null, null);
        int a = 1;
        if (cursor1.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                String number = cursor1.getString(cursor1.getColumnIndex("number"));
                // String name = cursor1.getString(cursor1.getColumnIndex("name"));
                if (number.equals(xuehao)) {
                    Intent intent;
                    intent = new Intent(this, Piano_guanzhu.class);
                    startActivity(intent);
                    a = 0;
                }
            } while (cursor1.moveToNext());
        }
        if (a == 1) {
            Intent intent;
            intent = new Intent(this, Piano.class);
            startActivity(intent);
        }

    }
}