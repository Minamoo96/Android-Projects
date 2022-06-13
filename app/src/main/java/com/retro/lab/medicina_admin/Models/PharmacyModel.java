package com.retro.lab.medicina_admin.Models;

import com.google.gson.annotations.SerializedName;

public class PharmacyModel {

    @SerializedName("id")
    private int id;
    @SerializedName("pharmace_name")
    private String pharmace_name;
    @SerializedName("phone_number")
    private String number;
    @SerializedName("address")
    private String address;
    @SerializedName("serial_number")
    private String serial_number;

    public PharmacyModel(int id, String name, String number, String address, String serial_number){

        this.id = id;
        this.pharmace_name = name;
        this.number = number;
        this.address = address;
        this.serial_number = serial_number;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPharmace_name() {
        return pharmace_name;
    }

    public void setPharmace_name(String pharmace_name) {
        this.pharmace_name = pharmace_name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

}
