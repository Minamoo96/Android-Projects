package com.retro.lab.medicina_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

public class MedicinesActivity extends AppCompatActivity {

    TextView adminName;
    RecyclerView recyclerView;
    AppCompatButton addMedicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);
    }
}