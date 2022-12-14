package com.example.caza.join;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.RadioButton;

import com.example.caza.APIInterface;
import com.example.caza.MainActivity;
import com.example.caza.R;
import com.example.caza.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Activity_3 extends AppCompatActivity {

    private Button button_register2;

    RadioButton radioButton_customer2;
    RadioButton radioButton_owner2;

    EditText id1;
    EditText password1;
    EditText name1;
    EditText number1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        radioButton_customer2 = (RadioButton) findViewById(R.id.radioButton_customer2);
        radioButton_owner2 = (RadioButton) findViewById(R.id.radioButton_owner2);

        id1 = (EditText) findViewById(R.id.register_id);
        password1 = (EditText) findViewById(R.id.register_pass);
        name1 = (EditText) findViewById(R.id.editTextTextPassword2);
        number1 = (EditText) findViewById(R.id.editTextTextPassword3);


        button_register2 = findViewById(R.id.button_register2);

        APIInterface service = RetrofitClient.getClient().create(APIInterface.class);

        button_register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = id1.getText().toString();
                String password = password1.getText().toString();
                String name = name1.getText().toString();
                String number = number1.getText().toString();
                String flag = "";


                if (radioButton_customer2.isChecked() == true) {
                    flag = "client";
                }
                else if (radioButton_owner2.isChecked() == true){
                    flag = "owner";
                }
                else {
                    Toast.makeText(getApplicationContext(), "?????? ????????? ??????????????????", Toast.LENGTH_SHORT).show();
                }

                if((id1.length() == 0) ||(password1.length() == 0) || (name1.length() == 0) || (number1.length() == 0)){
                    Toast.makeText(getApplicationContext(), "????????? ??????????????????",Toast.LENGTH_SHORT).show();
                } else {
                    service.userJoin(new JoinData(id,password,name,number,flag)).enqueue(new Callback<JoinResponse>() {
                        @Override
                        public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                            JoinResponse result = response.body();
                            //?????????????????? ????????? ????????? ????????? JoinResponse????????? ?????????.
                            Toast.makeText(Activity_3.this, result.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Activity_3.this, MainActivity.class);
                            // getMessage??? ?????? ????????? ??????????????? ???????????? ??????????????? ???????????? ??????
                            if(result.getStatus() == 200) {
                                startActivity(intent);
                                Activity_3.this.finish();  //getStatus??? ????????? ????????? 200(OK)??? ???????????? ??????????????? ??????
                                //????????? ???????????? ?????????????????????
                            }
                        }

                        @Override
                        public void onFailure(Call<JoinResponse> call, Throwable t) {
                            Toast.makeText(Activity_3.this, "Sign up Error", Toast.LENGTH_SHORT).show();
                            Log.e("Sign up Error", t.getMessage());
                            t.printStackTrace();
                        }
                    });
                }

            }
        });
    }
}