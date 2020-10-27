package com.example.myfinalwork.ui.home;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myfinalwork.DBHelper;
import com.example.myfinalwork.MainActivity;
import com.example.myfinalwork.R;
import com.example.myfinalwork.jitashe.Jita_chengyuanzongji;
import com.example.myfinalwork.ui.login.LoginViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.myfinalwork.ui.login.LoginActivity.xuehao;

public class HomeFragment extends Fragment {
    private LoginViewModel loginViewModel;
    private DBHelper dbHelper;
    private HomeViewModel homeViewModel;
    private ImageView touxiang ;
    private Button fanhui,tijiao;
    private EditText name1,sex1,age1,major1;
    View root;
    List<HashMap<String, String>> retList = new ArrayList<HashMap<String, String>>();
    ListView mylistview;
    private SimpleAdapter listItemAdapter;//适配器

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
        touxiang=root.findViewById(R.id.imageView2);
        //传值
        return root;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        //获取输入数据
        name1=root.findViewById(R.id.name1);
        sex1=root.findViewById(R.id.sex1);
        age1=root.findViewById(R.id.age1);
        major1=root.findViewById(R.id.major1);
        //点击修改并提交按钮跳转到Home_chuanzhi页面，连接数据库保存数据
        tijiao=root.findViewById(R.id.tijiao);
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent;
                intent=new Intent(getActivity(), Home_chuanzhi.class);
                Bundle bundle=new Bundle();
                String name=name1.getText().toString();
                String sex=sex1.getText().toString();
                String age=age1.getText().toString();
                String major=major1.getText().toString();
                bundle.putString("name",name);
                bundle.putString("sex",sex);
                bundle.putString("age",age);
                bundle.putString("major",major);
                intent.putExtras(bundle);
                startActivityForResult(intent,2);
            }
        });
        //导入数据库
        dbHelper = new DBHelper(getActivity(),"MyFinalWork.db",null,1);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

//在INFORMATION里查找用户相关信息
        Cursor cursor1 = db.query("INFORMATION", null, null, null, null, null, null);
        if (cursor1.moveToFirst()) {
            do {
                String number = cursor1.getString(cursor1.getColumnIndex("number"));
                String name = cursor1.getString(cursor1.getColumnIndex("name"));
                String age = cursor1.getString(cursor1.getColumnIndex("age"));
                String sex = cursor1.getString(cursor1.getColumnIndex("sex"));
                String major= cursor1.getString(cursor1.getColumnIndex("major"));
                if(number.equals(xuehao)){
                    name1.setText(name);
                    sex1.setText(sex);
                    age1.setText(age);
                    major1.setText(major);
                }
            } while (cursor1.moveToNext());
        }

//返回到主页面
        fanhui= (Button) getActivity().findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent;
            intent=new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
            }
        });
//个人信息页面里显示加入社团信息
        //与页面布局文件中的listview关联
        mylistview = (ListView) getActivity().findViewById(R.id.list_shetuan);
        //将所有数据库中的信息取出存入成员名单retList中在listview逐条中显示
        Cursor cursor2 = db.query("JINFORMATION", null, null, null, null, null, null);
        if (cursor2.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                String number = cursor2.getString(cursor1.getColumnIndex("number"));
                String shetuan = cursor1.getString(cursor1.getColumnIndex("shetuan"));
                if(number.equals(xuehao)){
                    map.put("shetuan", shetuan);
                    retList.add(map);
                }

            } while (cursor1.moveToNext());
        }
        listItemAdapter = new SimpleAdapter(getActivity(), retList, //listItem数据源
                R.layout.list_chengyuan,
                //ListItem的XML布局实现
                new String[]{"name"},
                //数据的key
                new int[]{R.id.shetuan}//布局里的id，k与id一一匹配
        );
        //listview.setAdapter(myAdapter);
        mylistview.setAdapter(listItemAdapter);
        mylistview.setEmptyView(getActivity().findViewById(R.id.nodata));//当列表没有数据时显示设置
    }

}