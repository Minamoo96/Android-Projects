package com.retro.lab.medicina_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.retro.lab.medicina_admin.Adapters.MedicinesAdapter;
import com.retro.lab.medicina_admin.Adapters.PharmacyAdapter;
import com.retro.lab.medicina_admin.Models.AdminModel;
import com.retro.lab.medicina_admin.Models.MedicinesModel;
import com.retro.lab.medicina_admin.Models.PharmacyModel;
import com.retro.lab.medicina_admin.Services.SharedPref;
import com.retro.lab.medicina_admin.Services.URLS;
import com.retro.lab.medicina_admin.Services.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class PharmaciesActivity extends AppCompatActivity {

    TextView adminName;
    RecyclerView recyclerView;
    AppCompatButton addPharmacy;

    public  static PharmacyAdapter mExampleAdapter;
    public  static ArrayList<PharmacyModel> mExampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacies);

        adminName = findViewById(R.id.adminName1);
        AdminModel adminModel = (AdminModel) SharedPref.getInstance(getApplicationContext()).getUser();
        String name = adminModel.getUsername();
        adminName.setText("Admin: " + name);

        recyclerView =findViewById(R.id.pharmaciesRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        mExampleAdapter = new PharmacyAdapter(PharmaciesActivity.this, mExampleList);
        recyclerView.setAdapter(mExampleAdapter);

        getPharmacies();

        addPharmacy = findViewById(R.id.addPharmacy);
        addPharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddPharmacyActivity.class));
            }
        });

    }

    public void getPharmacies() {
      mExampleList.clear();
        StringRequest request = new StringRequest(Request.Method.GET, URLS.URL_GET_All_PHARMACIES,new Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {
                try {
                    
                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.getJSONArray("row");

                    Gson gson = new Gson();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonobject = jsonArray.getJSONObject(i);
                        PharmacyModel emg = gson.fromJson(jsonobject.toString(), PharmacyModel.class);
                        mExampleList.add(emg);
                    }
                    mExampleAdapter.notifyDataSetChanged();
                } catch (Exception exception) {
                    Toast.makeText(PharmaciesActivity.this, "Exception: " + exception, Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Response:  " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);

    }
}