package com.example.myfinalwork.jitashe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myfinalwork.DBHelper;
import com.example.myfinalwork.MyActivityFragment;
import com.example.myfinalwork.R;

import static com.example.myfinalwork.ui.login.LoginActivity.xuehao;


public class Jitashe extends AppCompatActivity {
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    public static int chengyuangeshu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jitashe);
        dbHelper = new DBHelper(this, "MyFinalWork.db", null, 1);
        //统计一共有多少成员加入吉他社
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        Cursor cursor = db.rawQuery("select count(number) from JITA_INFORMATION", null);
//        cursor.moveToFirst();
//        chengyuangeshu = cursor.getInt(0);
//        cursor.close();
//        db.close();
//        TextView geshu = findViewById(R.id.textView6);
//        geshu.setText(chengyuangeshu);
//跳转回社团主页面
        Button fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Jitashe.this, MyActivityFragment.class);
                startActivity(intent);
            }
        });
        //点击成员按钮，跳转至成员界面
        TextView btn_chengyuan = findViewById(R.id.textView2);
        btn_chengyuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Jitashe.this, Jita_chengyuanzongji.class);
                startActivity(intent);
            }
        });
//点击关注按钮，跳转至已关注页面
        ImageButton guanzhu = findViewById(R.id.imageButton);
        guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("INFORMATION", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        String number = cursor.getString(cursor.getColumnIndex("number"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        if (number.equals(xuehao)) {
                            ContentValues values = new ContentValues();

                            values.put("number", number);
                            values.put("name", name);
                            db.insert("JITA_INFORMATION", null, values);
                            db.close();
                        }
                    } while (cursor.moveToNext());
                }
                Intent intent = new Intent(Jitashe.this, Jita_guanzhu.class);
                startActivity(intent);
            }
        });
    }
}
