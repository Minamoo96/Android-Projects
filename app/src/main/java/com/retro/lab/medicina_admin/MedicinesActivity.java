package com.retro.lab.medicina_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.retro.lab.medicina_admin.Models.MedicinesModel;
import com.retro.lab.medicina_admin.Services.URLS;
import com.retro.lab.medicina_admin.Services.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MedicinesActivity extends AppCompatActivity {

    TextView adminName;
    RecyclerView recyclerView;
    AppCompatButton addMedicine;

    private MedicinesAdapter mExampleAdapter;
    private ArrayList<MedicinesModel> mExampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicines);

        adminName = findViewById(R.id.adminName2);
        adminName.setText("Admin: Mohamed");


        recyclerView =findViewById(R.id.medicineRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        mExampleAdapter = new MedicinesAdapter(this, mExampleList);
        recyclerView.setAdapter(mExampleAdapter);

        getMedicines();

        addMedicine = findViewById(R.id.addMedicine);
        addMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddMedicineActivity.class));
            }
        });

    }

    public void getMedicines(){

        StringRequest request = new StringRequest(Request.Method.GET, URLS.URL_GET_All_Medicines,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.getJSONArray("row");

                    Gson gson = new Gson();
                    for (int i = 1; i < jsonArray.length(); i++) {
                        JSONObject jsonobject = jsonArray.getJSONObject(i);
                        MedicinesModel emg = gson.fromJson(jsonobject.toString(), MedicinesModel.class);
                        mExampleList.add(emg);
                    }
                    mExampleAdapter.notifyDataSetChanged();
                } catch (Exception exception) {
                    Toast.makeText(MedicinesActivity.this, "Exception: " + exception, Toast.LENGTH_LONG).show();
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