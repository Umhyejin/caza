package com.example.caza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Activity_2 extends AppCompatActivity {

    private RetrofitClient retrofitClient;
    private Button button_register;
    private Button button_login;

    RadioButton radioButton_customer2;
    RadioButton radioButton_owner2;

    EditText id1;
    EditText password1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        radioButton_customer2 = (RadioButton) findViewById(R.id.radioButton_customer2);
        radioButton_owner2 = (RadioButton) findViewById(R.id.radioButton_owner2);

        id1 = (EditText) findViewById(R.id.register_id);
        password1 = (EditText) findViewById(R.id.register_pass);

        button_register = findViewById(R.id.button_register);
        button_login = findViewById(R.id.button_login);

        APIInterface service = RetrofitClient.getClient().create(APIInterface.class);

        //자동 로그인을 선택한 유저
        /*if (!getPreferenceString(autoLoginId).equals("") && !getPreferenceString(autoLoginPw).equals("")) {
            checkBox.setChecked(true);
            checkAutoLogin(getPreferenceString(autoLoginId));
        }*/

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = id1.getText().toString();
                String password = password1.getText().toString();
                String flag = "";

                if (radioButton_customer2.isChecked() == true) {
                    flag = "client";
                }
                else if (radioButton_owner2.isChecked() == true){
                    flag = "owner";
                }
                else {
                    Toast.makeText(getApplicationContext(), "체크 여부를 확인해주세요", Toast.LENGTH_SHORT).show();
                }

                if((id.length() == 0) ||(password.length() == 0)){
                    Toast.makeText(getApplicationContext(), "정보를 입력해주세요",Toast.LENGTH_SHORT).show();
                } else {
                    String finalFlag = flag;
                    service.userLogin(new LoginData(id,password,flag)).enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                           // Log.d("retrofit", "Data fetch success");

                            //통신 성공
                         /*   if (response.isSuccessful() && response.body() != null) {

                                response.body()를 result에 저장
                                LoginResponse result = response.body();

                                //받은 코드 저장
                                String resultCode = result.getResultCode();

                                //받은 토큰 저장
                                String token = result.getToken();

                                String success = "200"; //로그인 성공
                                String errorId = "300"; //아이디 일치x
                                String errorPw = "400"; //비밀번호 일치x


                                if (resultCode.equals(success)) {

                                    다른 통신을 하기 위해 token 저장
                                    setPreference(token,token); */

                                    //자동 로그인
                                   /* if (checkBox.isChecked()) {
                                        setPreference(autoLoginId, userID);
                                        setPreference(autoLoginPw, userPassword);
                                    } else {
                                        setPreference(autoLoginId, "");
                                        setPreference(autoLoginPw, "");
                                    }*/

                                    if(finalFlag == "client"){
                                        Toast.makeText(Activity_2.this, id + "님 환영합니다.", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Activity_2.this, MainActivity.class);
                                        intent.putExtra("id", id);
                                        startActivity(intent);
                                        Activity_2.this.finish();
                                    }
                                    else if (finalFlag == "owner"){
                                        Toast.makeText(Activity_2.this, id + "님 환영합니다.", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(Activity_2.this, Activity_4.class);
                                        intent.putExtra("userId", id);
                                        startActivity(intent);
                                        Activity_2.this.finish();
                                    }

                              /*  } else if (resultCode.equals(errorId)) {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(Activity_2.this);
                                    builder.setTitle("알림")
                                            .setMessage("아이디가 존재하지 않습니다.")
                                            .setPositiveButton("확인", null)
                                            .create()
                                            .show();
                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();

                                } else if (resultCode.equals(errorPw)) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Activity_2.this);
                                    builder.setTitle("알림")
                                            .setMessage("비밀번호가 일치하지 않습니다.")
                                            .setPositiveButton("확인", null)
                                            .create()
                                            .show();
                                } else {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(Activity_2.this);
                                    builder.setTitle("알림")
                                            .setMessage("예기치 못한 오류가 발생하였습니다.\n 고객센터에 문의바랍니다.")
                                            .setPositiveButton("확인", null)
                                            .create()
                                            .show();

                                }
                            }
                        } */
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(Activity_2.this, "Sign up Error", Toast.LENGTH_SHORT).show();
                        Log.e("Sign up Error", t.getMessage());
                        t.printStackTrace();
                    }

                    });
                }

               // Intent intent = new Intent(Activity_2.this , Activity_3.class);
              //  startActivity(intent); // 액티비티 이동.

            }
        });

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_2.this , Activity_3.class);
                startActivity(intent); // 액티비티 이동.
            }
        });
    }
}
