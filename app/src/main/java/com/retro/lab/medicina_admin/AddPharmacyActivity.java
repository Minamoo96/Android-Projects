package com.retro.lab.medicina_admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.retro.lab.medicina_admin.Models.MedicinesModel;
import com.retro.lab.medicina_admin.Models.PharmacyModel;
import com.retro.lab.medicina_admin.Services.URLS;
import com.retro.lab.medicina_admin.Services.VolleySingleton;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddPharmacyActivity extends AppCompatActivity {

    EditText pName, pAddress, pNumber, pSerial;
    AppCompatButton savePharmacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pharmacy);

        pName = findViewById(R.id.pName);
        pAddress = findViewById(R.id.pAddress);
        pNumber = findViewById(R.id.pNumber);
        pSerial = findViewById(R.id.pSerial);

        savePharmacy = findViewById(R.id.savePharmacy);
        savePharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPharmacy(pName.getText().toString(),
                        pAddress.getText().toString(),
                        pNumber.getText().toString(),
                        pSerial.getText().toString());
            }
        });

    }

    private void addPharmacy(String pName, String pAddress, String pNumber, String pSerial) {

        StringRequest request = new StringRequest(Request.Method.POST, URLS.NEW_PHARMACY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("error")){
                        Toast.makeText(AddPharmacyActivity.this, "response: " + object.getString("message"), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(AddPharmacyActivity.this, "response: " + object.getString("message"), Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }catch (Exception exception){
                    Toast.makeText(AddPharmacyActivity.this, "Exception: " + exception, Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("pharmace_name", pName);
                params.put("address", pAddress);
                params.put("phone_number",pNumber);
                params.put("serial_number",pSerial);
                return params;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);

    }
}