package com.example.myfinalwork.jitashe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.myfinalwork.DBHelper;
import com.example.myfinalwork.MyActivityFragment;
import com.example.myfinalwork.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Jita_chengyuanzongji extends AppCompatActivity {
    List<HashMap<String, String>> retList = new ArrayList<HashMap<String, String>>();
    ListView mylistview;
    private DBHelper dbHelper;
    private SimpleAdapter listItemAdapter;//适配器

    //存放文字、图片信息
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jita_chengyuanzongji);

//与页面布局文件中的listview关联
        mylistview = (ListView) findViewById(R.id.mylistview);

        //创建个人信息数据库
        dbHelper = new DBHelper(this, "MyFinalWork.db", null, 1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //将所有数据库中的信息取出存入成员名单retList中在listview逐条中显示
        Cursor cursor1 = db.query("JITA_INFORMATION", null, null, null, null, null, null);
        if (cursor1.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                String number = cursor1.getString(cursor1.getColumnIndex("number"));
                String name = cursor1.getString(cursor1.getColumnIndex("name"));
                map.put("name", name);
                map.put("number", number);
                retList.add(map);
            } while (cursor1.moveToNext());
        }
        listItemAdapter = new SimpleAdapter(Jita_chengyuanzongji.this, retList, //listItem数据源
                R.layout.list_chengyuan,
                //ListItem的XML布局实现
                new String[]{"name", "number"},
                //数据的key
                new int[]{R.id.chengyuan_name, R.id.chengyuan_number}//布局里的id，k与id一一匹配
        );
        //listview.setAdapter(myAdapter);
        mylistview.setAdapter(listItemAdapter);
        mylistview.setEmptyView(findViewById(R.id.nodata));//当列表没有数据时显示设置
//返回吉他社主页面
        Button fanhui=findViewById(R.id.fanhui3);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Jita_chengyuanzongji.this, Jitashe.class);
                startActivity(intent);
            }
        });
    }


}