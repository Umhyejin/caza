package com.example.caza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_4 extends AppCompatActivity {

    private Button button_caferegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        button_caferegister = findViewById(R.id.button_caferegister);
        button_caferegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_4.this, Activity_5.class);
                startActivity(intent);
            }
        });
    }
}