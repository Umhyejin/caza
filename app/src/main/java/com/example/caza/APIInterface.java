package com.example.caza;

import com.example.caza.join.JoinData;
import com.example.caza.join.JoinResponse;
import com.example.caza.login.LoginData;
import com.example.caza.login.LoginResponse;

import retrofit2.Call;
import retrofit2.http.*;

public interface APIInterface {

    @Headers({"Content-Type: application/json"})
    @POST("/login")
    Call<LoginResponse> userLogin(@Body LoginData data);

    @Headers({"Content-Type: application/json"})
    @POST("/join")
    Call<JoinResponse> userJoin(@Body JoinData data);


    // @FormUrlEncoded
    //@POST("/join")
   // Call<ResponseBody> postFunc(@Field("data") String data);
}
