package com.example.myfinalwork.Piano;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myfinalwork.DBHelper;
import com.example.myfinalwork.MainActivity;
import com.example.myfinalwork.R;
import com.example.myfinalwork.jitashe.Jita_chengyuanzongji;
import com.example.myfinalwork.jitashe.Jita_guanzhu;

public class Piano_guanzhu extends AppCompatActivity {
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano_guanzhu);
        //创建个人信息数据库
        dbHelper = new DBHelper(this, "MyFinalWork.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count=0;
        //将所有数据库中的信息取出存入成员名单retList中在listview逐条中显示
        Cursor cursor1 = db.query("PIANO_INFORMATION", null, null, null, null, null, null);
        if (cursor1.moveToFirst()) {
            do {
                count++;
            } while (cursor1.moveToNext());
        }
        //统计成员个数显示在texeview中
        TextView chengyuan=findViewById(R.id.textView6);
        String count1=String.valueOf(count);
        chengyuan.setText(count1);
        //跳转回社团主页面
        Button fanhui=findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Piano_guanzhu.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //点击成员按钮，跳转至成员界面
        TextView btn_chengyuan = findViewById(R.id.textView2);
        btn_chengyuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Piano_guanzhu.this, Piano_chengyuanzongji.class);
                startActivity(intent);
            }
        });
    }
}