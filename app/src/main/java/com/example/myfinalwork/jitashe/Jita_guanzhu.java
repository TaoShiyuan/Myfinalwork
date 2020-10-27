package com.example.myfinalwork.jitashe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myfinalwork.MyActivityFragment;
import com.example.myfinalwork.R;

public class Jita_guanzhu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jita_guanzhu);
        //跳转回社团主页面
        Button fanhui=findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Jita_guanzhu.this, MyActivityFragment.class);
                startActivity(intent);
            }
        });
        //点击成员按钮，跳转至成员界面
        TextView btn_chengyuan = findViewById(R.id.textView2);
        btn_chengyuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Jita_guanzhu.this, Jita_chengyuanzongji.class);
                startActivity(intent);
            }
        });
    }

}