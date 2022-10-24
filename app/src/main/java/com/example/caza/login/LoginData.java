package com.example.caza.login;

import com.google.gson.annotations.SerializedName;

public class LoginData {

    @SerializedName("id")
    private String id;

    @SerializedName("password")
    private String password;

    @SerializedName("flag")
    private String flag;

    public LoginData(String id, String password, String flag) {
        this.id=id;
        this.password=password;
        this.flag=flag;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getFlag() {
        return flag;
    }

    public void setInputId(String id) {
        this.id = id;
    }

    public void setInputPw(String password) {
        this.password = password;
    }

    public void setInputFlag(String flag) {
        this.flag = flag;
    }
}
