package com.example.caza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_3 extends AppCompatActivity {

    private Button button_register2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        button_register2 = findViewById(R.id.button_register2);
        button_register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_3.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}