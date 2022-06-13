package com.retro.lab.medicina_admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.retro.lab.medicina_admin.Models.AdminModel;
import com.retro.lab.medicina_admin.Models.PharmacyModel;
import com.retro.lab.medicina_admin.Services.SharedPref;
import com.retro.lab.medicina_admin.Services.URLS;
import com.retro.lab.medicina_admin.Services.VolleySingleton;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateMedicineActivity extends AppCompatActivity {

    EditText meddPrice;
    TextView medName, phhname, phhAddress, meddSerial, adminName;
    AppCompatButton updateMed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_medicine);



        medName = findViewById(R.id.meddName);
        meddPrice = findViewById(R.id.meddPrice);

        phhname = findViewById(R.id.phhName);
        phhAddress = findViewById(R.id.phhAddress);
        meddSerial = findViewById(R.id.meddSerial);

        updateMed = findViewById(R.id.updateMedicine);

        Intent intent = getIntent();
        int id = intent.getIntExtra("Med_id", 0);
        String medicineName = intent.getStringExtra("Med_Name");
        String medicinePrice = intent.getStringExtra("Med_price");
        String pharmacyName = intent.getStringExtra("Ph_Name");
        String pharmacyAddress = intent.getStringExtra("Ph_Address");
        String medicineSerial = intent.getStringExtra("Med_Serial");

        medName.setText(medicineName);
        meddPrice.setHint(medicinePrice);

        phhname.setText(pharmacyName);
        phhAddress.setText(pharmacyAddress);
        meddSerial.setText(medicineSerial);

        updateMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateMedicine(id , meddPrice.getText().toString());
            }
        });

    }

    public void updateMedicine( int id, String medPrice){

        StringRequest request = new StringRequest(Request.Method.POST, URLS.URL_UPDATE_MEDICINES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("error")){
                        Toast.makeText(UpdateMedicineActivity.this, "response: " + object.getString("message"), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(UpdateMedicineActivity.this, "Response: "+object.getString("message"), Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                }catch (Exception exception){
                    Toast.makeText(UpdateMedicineActivity.this, "Exception: " + exception, Toast.LENGTH_LONG).show();
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
                params.put("id", String.valueOf(id));
                params.put("price", medPrice);
                return params;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);

    }

}