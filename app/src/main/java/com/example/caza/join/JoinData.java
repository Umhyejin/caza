package com.example.caza.join;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("id")
    private String id;

    @SerializedName("password")
    private String password;

    @SerializedName("name")
    private String name;

    @SerializedName("number")
    private String number;

    @SerializedName("flag")
    private String flag;

    public JoinData(String id, String password, String name, String number, String flag) {
        this.id=id;
        this.password=password;
        this.name=name;
        this.number=number;
        this.flag=flag;
    }

    public void setUser(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String number) {
        this.number = number;
    }

    public void setType(String flag) {
        this.flag = flag;
    }
}
