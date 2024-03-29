package com.retro.lab.medicina_admin.Models;

import com.google.gson.annotations.SerializedName;

public class AdminModel {

    @SerializedName("id")
    private int id;
    @SerializedName("username")
    private String username;
    @SerializedName("Password")
    private String password;

    public AdminModel(int id, String username, String password){

        this.id = id;
        this.username = username;
        this.password = password;

    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
