package com.example.myfinalwork.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfinalwork.DBHelper;
import com.example.myfinalwork.MainActivity;
import com.example.myfinalwork.MyActivityFragment;
import com.example.myfinalwork.R;
import com.example.myfinalwork.jitashe.Jitashe;
import com.example.myfinalwork.ui.login.LoginActivity;

import static com.example.myfinalwork.ui.login.LoginActivity.xuehao;

public class Home_chuanzhi extends FragmentActivity {
    private DBHelper dbHelper;
    EditText Name,Sex,Age,Major;
    private ImageView touxiang ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home1);
        //传值,未获取到。
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String name1=bundle.getString("name","");
        String sex1=bundle.getString("sex","");
        String age1=bundle.getString("age","");
        String major1=bundle.getString("major","");
        //        将个人信息页面的默认值修改为刚刚修改的值
        Name=findViewById(R.id.name1);
        Sex=findViewById(R.id.sex1);
        Major=findViewById(R.id.major1);
        Age=findViewById(R.id.age1);
        Name.setText(name1);
        Sex.setText(sex1);
        Major.setText(major1);
        Age.setText(age1);
        touxiang=findViewById(R.id.imageView2);
        touxiang.setImageDrawable(getResources().getDrawable((R.drawable.touxiang)));//头像未传递到更新页面
        //连接数据库
        dbHelper=new DBHelper(this,"MyFinalWork.db",null,1);
        SQLiteDatabase db2=dbHelper.getWritableDatabase();
        db2.execSQL("update INFORMATION set name='"+name1+"',sex='"+sex1+"',age='"+age1+"',major='"+major1+"' where number='"+xuehao+"'");
        db2.close();
        Toast.makeText(getApplicationContext(),"成功提交", Toast.LENGTH_LONG).show();
//返回主页面

}
}