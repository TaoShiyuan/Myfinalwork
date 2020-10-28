package com.example.myfinalwork.Piano;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myfinalwork.DBHelper;
import com.example.myfinalwork.MainActivity;
import com.example.myfinalwork.R;
import com.example.myfinalwork.jitashe.Jita_chengyuanzongji;
import com.example.myfinalwork.jitashe.Jita_guanzhu;
import com.example.myfinalwork.jitashe.Jitashe;

import static com.example.myfinalwork.ui.login.LoginActivity.xuehao;

public class Piano extends AppCompatActivity {
    private DBHelper dbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano);
        //创建个人信息数据库
        dbHelper = new DBHelper(this, "MyFinalWork.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = 0;
        //将所有数据库中的信息取出存入成员名单retList中在listview逐条中显示
        Cursor cursor1 = db.query("PIANO_INFORMATION", null, null, null, null, null, null);
        if (cursor1.moveToFirst()) {
            do {
                count++;
            } while (cursor1.moveToNext());
        }
        //统计成员个数显示在texeview中
        TextView chengyuan = findViewById(R.id.textView6);
        String count1 = String.valueOf(count);
        chengyuan.setText(count1);
//跳转回社团主页面
        Button fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Piano.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //点击成员按钮，跳转至成员界面
        TextView btn_chengyuan = findViewById(R.id.textView2);
        btn_chengyuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Piano.this, Piano_chengyuanzongji.class);
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
                        String shetuan = cursor.getString(cursor.getColumnIndex("shetuan_1"));
                        if (number.equals(xuehao)) {
                            //将学生信息加入JITA_INFORMATION中
                            ContentValues values = new ContentValues();
                            values.put("number", number);
                            values.put("name", name);
                            db.insert("PIANO_INFORMATION", null, values);
                            //将社团信息加入INFORMATION中,取出来再用split逗号分割放到个人信息的listview中
                            String shetuan1 = shetuan + "钢琴社,";
                            db.execSQL("update INFORMATION set shetuan_1='" + shetuan1 + "' where number='" + number + "'");
                            db.close();
                        }
                    } while (cursor.moveToNext());
                }
                Intent intent = new Intent(Piano.this, Piano_guanzhu.class);
                startActivity(intent);
            }
        });
    }
}