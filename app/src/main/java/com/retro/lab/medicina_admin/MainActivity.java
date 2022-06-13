package com.retro.lab.medicina_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.retro.lab.medicina_admin.Models.AdminModel;
import com.retro.lab.medicina_admin.Services.SharedPref;

public class MainActivity extends AppCompatActivity {

    CardView users, pharmacies, medicines;
    TextView admin;
    AppCompatButton logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        admin = findViewById(R.id.adminUser);
        AdminModel adminModel = (AdminModel) SharedPref.getInstance(getApplicationContext()).getUser();
        String name = adminModel.getUsername();
        admin.setText("Admin: " + name);

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPref.getInstance(MainActivity.this).logOut();
            }
        });

        users = findViewById(R.id.users);
        pharmacies = findViewById(R.id.pharmacies);
        medicines = findViewById(R.id.medicines);

        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserShowActivity.class));
            }
        });
        pharmacies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PharmaciesActivity.class));
            }
        });
        medicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MedicinesActivity.class));
            }
        });
    }
}