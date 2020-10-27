package com.example.myfinalwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.myfinalwork.ui.gallery.GalleryFragment;

public class MyActivityFragment extends FragmentActivity {

    private Fragment mFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_my_fragment);

        fragmentManager=getSupportFragmentManager();

        mFragment=fragmentManager.findFragmentById(R.id.nav_gallery);
        fragmentTransaction=fragmentManager.beginTransaction().hide(mFragment);
        fragmentTransaction.show(mFragment).commit();
        //TextView t=findViewById(R.id.name1);





    }

}