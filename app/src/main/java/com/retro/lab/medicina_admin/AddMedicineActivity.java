package com.retro.lab.medicina_admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.retro.lab.medicina_admin.Models.AdminModel;
import com.retro.lab.medicina_admin.Models.MedicinesModel;
import com.retro.lab.medicina_admin.Models.PharModel;
import com.retro.lab.medicina_admin.Models.PharmacyModel;
import com.retro.lab.medicina_admin.Services.SharedPref;
import com.retro.lab.medicina_admin.Services.URLS;
import com.retro.lab.medicina_admin.Services.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddMedicineActivity extends AppCompatActivity {

    EditText medName, medPrice, medSerial;
    AppCompatButton saveMedicine;
    TextView admin;

    private ArrayList<PharModel> goodModelArrayList;
    private ArrayList<String> names = new ArrayList<String>();
    private Spinner phName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        admin = findViewById(R.id.adminName3);
        AdminModel adminModel = (AdminModel) SharedPref.getInstance(getApplicationContext()).getUser();
        String name = adminModel.getUsername();
        admin.setText("Admin: " + name);


        medName = findViewById(R.id.medName);
        phName = findViewById(R.id.phName);
        medPrice = findViewById(R.id.medPrice);
        medSerial = findViewById(R.id.medSerial);
        saveMedicine = findViewById(R.id.saveMedicine);
        saveMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tempID =1;
                for (PharModel obj: goodModelArrayList) {
                    if(String.valueOf(phName.getSelectedItem().toString().trim()).equals(obj.getPharmacy_name())){
                        tempID = obj.getId();
                        break;
                    }
                }

                addMedicine(medName.getText().toString(),
                        String.valueOf(tempID),
                        medPrice.getText().toString()
                );

            }
        });

        getPharmacies();
    }

    private void addMedicine(String meNname, String phId, String mePrice) {

        StringRequest request = new StringRequest(Request.Method.POST, URLS.NEW_MEDICINE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("error")){
                        Toast.makeText(AddMedicineActivity.this, "response: " + object.getString("message"), Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }else {
                        Toast.makeText(AddMedicineActivity.this, "Response: "+object.getString("message"), Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }catch (Exception exception){
                    Toast.makeText(AddMedicineActivity.this, "Exception: " + exception, Toast.LENGTH_LONG).show();
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
                params.put("medicin_name", meNname);
                params.put("pharmace_id", phId);
                params.put("price",mePrice);

                return params;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);

    }

    public void getPharmacies(){

        StringRequest request = new StringRequest(Request.Method.GET, URLS.GET_PHARMACY, new Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {
                try {

                    goodModelArrayList = new ArrayList<>();
                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.getJSONArray("row");


                    Gson gson = new Gson();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonobject = jsonArray.getJSONObject(i);
                        PharModel emg = gson.fromJson(jsonobject.toString(), PharModel.class);
                        goodModelArrayList.add(emg);
                    }


                    for (int i = 0; i < goodModelArrayList.size(); i++){
                        names.add(goodModelArrayList.get(i).getPharmacy_name().toString());
                    }

                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(AddMedicineActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, names);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                    phName.setAdapter(spinnerArrayAdapter);

//                    Toast.makeText(AddMedicineActivity.this, "Exception: " +  , Toast.LENGTH_LONG).show();


                } catch (Exception exception) {
                    Toast.makeText(AddMedicineActivity.this, "Exception: " + exception, Toast.LENGTH_LONG).show();
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