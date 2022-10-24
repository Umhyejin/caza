package com.example.caza;

import com.google.gson.annotations.SerializedName;

public class JoinResponse {

    @SerializedName("status")
    private int status;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public class Data{
        @SerializedName("id_client")
        private int id_client;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}