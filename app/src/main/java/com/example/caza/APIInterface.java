package com.example.caza;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.Contract;

import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.ResponseBody;

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
