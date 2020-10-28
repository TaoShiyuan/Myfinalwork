package com.example.myfinalwork.jitashe;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myfinalwork.DBHelper;
import com.example.myfinalwork.R;
import com.example.myfinalwork.jitashe.Jita_guanzhu;
import com.example.myfinalwork.jitashe.Jitashe;

import java.util.HashMap;

import static com.example.myfinalwork.ui.login.LoginActivity.xuehao;

public class GuanzhuOrNot extends FragmentActivity {
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jitashe);
        dbHelper = new DBHelper(this, "MyFinalWork.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
//在JITA_INFORMATION里查找是否存在本用户，如果有则跳转JITA_GUANZHU页面，如果无跳转至JITASHE页面
        Cursor cursor1 = db.query("JITA_INFORMATION", null, null, null, null, null, null);
        int a = 1;
        if (cursor1.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                String number = cursor1.getString(cursor1.getColumnIndex("number"));
                // String name = cursor1.getString(cursor1.getColumnIndex("name"));
                if (number.equals(xuehao)) {
                    Intent intent;
                    intent = new Intent(this, Jita_guanzhu.class);
                    startActivity(intent);
                    a = 0;
                }
            } while (cursor1.moveToNext());
        }
        if (a == 1) {
            Intent intent;
            intent = new Intent(this, Jitashe.class);
            startActivity(intent);
        }

    }

}