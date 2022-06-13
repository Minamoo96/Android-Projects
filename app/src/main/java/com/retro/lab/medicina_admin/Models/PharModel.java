package com.retro.lab.medicina_admin.Models;

import com.google.gson.annotations.SerializedName;

public class PharModel {

    @SerializedName("id")
    private int id;
    @SerializedName("pharmace_name")
    private String pharmacy_name;

    public PharModel(int id, String pharmacy_name){

        this.id = id;
        this.pharmacy_name = pharmacy_name;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPharmacy_name() {
        return pharmacy_name;
    }

    public void setPharmacy_name(String pharmacy_name) {
        this.pharmacy_name = pharmacy_name;
    }
}
