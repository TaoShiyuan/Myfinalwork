package com.example.myfinalwork.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myfinalwork.MainActivity;
import com.example.myfinalwork.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    ImageView jita_pic,dance_pic,piano_pic;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.textView3);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        jita_pic= (ImageView) getActivity().findViewById(R.id.jita_pic);

        jita_pic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent;
                intent=new Intent(getActivity(), GuanzhuOrNot.class);
                startActivity(intent);
            }
        });

        //返回到主页面
//        Button fanhui= (Button) getActivity().findViewById(R.id.fanhui);
//        fanhui.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Intent intent;
//                intent=new Intent(getActivity(), MainActivity.class);
//                startActivity(intent);
//            }
//        });
    }


}