package com.example.caza;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class Activity_9 extends AppCompatActivity {
    EditText cafe_name, cafe_time, cafe_phone, cafe_intro;
    ImageView imageView1, imageView2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_9);
        cafe_name = findViewById(R.id.cafe_name);
        cafe_time = findViewById(R.id.cafe_time);
        cafe_phone = findViewById(R.id.cafe_phone);
        cafe_intro = findViewById(R.id.cafe_hash);

        // drawable 리소스 객체 가져오기
        Drawable drawable = getResources().getDrawable(
                R.drawable.ediya);

        // XML 에 있는 ImageView 위젯에 이미지 셋팅
        ImageView imageView = (ImageView) findViewById(R.id.imageView1);
        imageView.setImageDrawable(drawable);






    }
}