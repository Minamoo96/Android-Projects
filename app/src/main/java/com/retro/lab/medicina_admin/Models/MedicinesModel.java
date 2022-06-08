package com.retro.lab.medicina_admin.Models;

import com.google.gson.annotations.SerializedName;

public class MedicinesModel {

    @SerializedName("id")
    private int id;
    @SerializedName("medicin_name")
    private String name ;
    @SerializedName("price")
    private String price ;
    @SerializedName("pharmace_name")
    private String pharmacy_name;
    @SerializedName("address")
    private String address;
    @SerializedName("serial_number")
    private String serial;

    public MedicinesModel(int id, String name, String pharmacy_name, String price, String address, String serial) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pharmacy_name = pharmacy_name;
        this.address = address;
        this.serial = serial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPharmacy_name(){
        return pharmacy_name;
    }

    public void setPharmacy_name(String pharmacy_name){
        this.pharmacy_name = pharmacy_name;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getSerial(){
        return serial;
    }

    public void setSerial(String serial){
        this.serial = serial;
    }

}
