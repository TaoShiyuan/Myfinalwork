package com.example.myfinalwork.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfinalwork.DBHelper;
import com.example.myfinalwork.MainActivity;
import com.example.myfinalwork.MyActivityFragment;
import com.example.myfinalwork.R;
import com.example.myfinalwork.jitashe.Jitashe;
import com.example.myfinalwork.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.myfinalwork.ui.login.LoginActivity.xuehao;

public class Home_chuanzhi extends FragmentActivity {
    private DBHelper dbHelper;
    EditText Name, Sex, Age, Major;
    private ImageView touxiang;
    List<HashMap<String, String>> retList = new ArrayList<HashMap<String, String>>();
    ListView mylistview;
    private SimpleAdapter listItemAdapter;//适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home1);
        //传值,未获取到。
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String name1 = bundle.getString("name", "");
        String sex1 = bundle.getString("sex", "");
        String age1 = bundle.getString("age", "");
        String major1 = bundle.getString("major", "");
        //        将个人信息页面的默认值修改为刚刚修改的值
        Name = findViewById(R.id.name1);
        Sex = findViewById(R.id.sex1);
        Major = findViewById(R.id.major1);
        Age = findViewById(R.id.age1);
        Name.setText(name1);
        Sex.setText(sex1);
        Major.setText(major1);
        Age.setText(age1);
        touxiang = findViewById(R.id.imageView2);
        if(sex1.equals("男")){
            touxiang.setImageDrawable(getResources().getDrawable((R.drawable.boy)));
        }
        else if(sex1.equals("女")){
            touxiang.setImageDrawable(getResources().getDrawable((R.drawable.girl)));
        }
        //touxiang.setImageDrawable(getResources().getDrawable((R.drawable.touxiang)));
        //连接数据库
        dbHelper = new DBHelper(this, "MyFinalWork.db", null, 1);
        SQLiteDatabase db2 = dbHelper.getWritableDatabase();
        db2.execSQL("update INFORMATION set name='" + name1 + "',sex='" + sex1 + "',age='" + age1 + "',major='" + major1 + "' where number='" + xuehao + "'");
        db2.execSQL("update JITA_INFORMATION set name='" + name1 + "' where number='" + xuehao + "'");
        Toast.makeText(getApplicationContext(), "成功提交", Toast.LENGTH_LONG).show();
//        //个人信息页面里显示加入社团信息
//        //与页面布局文件中的listview关联
//        mylistview = (ListView) findViewById(R.id.list_shetuan);
//        //将所有数据库中的信息取出存入成员名单retList中在listview逐条中显示
//        Cursor cursor2 = db2.query("INFORMATION", null, null, null, null, null, null);
//        if (cursor2.moveToFirst()) {
//            do {
//
//                String number = cursor2.getString(cursor2.getColumnIndex("number"));
//                String shetuan = cursor2.getString(cursor2.getColumnIndex("shetuan_1"));
//                if (shetuan == null) {
//                    shetuan = "";
//                }
//                if (number.equals(xuehao)) {
//                    String[] result = shetuan.split(",|null");
//                    for (int i = 1; i < result.length; i++) {
//                        HashMap<String, String> map = new HashMap<String, String>();
//                        System.out.println(result[i]);
//                        map.put("shetuan", result[i]);
//                        retList.add(map);
//                    }
//                }
//            } while (cursor2.moveToNext());
//        }
//        listItemAdapter = new SimpleAdapter(this, retList, //listItem数据源
//                R.layout.list_shetuan,
//                //ListItem的XML布局实现
//                new String[]{"shetuan"},
//                //数据的key
//                new int[]{R.id.shetuan}//布局里的id，k与id一一匹配
//        );
//        //listview.setAdapter(myAdapter);
//        mylistview.setAdapter(listItemAdapter);
//        mylistview.setEmptyView(findViewById(R.id.nodata3));//当列表没有数据时显示设置
        db2.close();
//返回主页面
        Button fanhui = findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(Home_chuanzhi.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}