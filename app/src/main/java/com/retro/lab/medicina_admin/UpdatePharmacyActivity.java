package com.retro.lab.medicina_admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.retro.lab.medicina_admin.Services.URLS;
import com.retro.lab.medicina_admin.Services.VolleySingleton;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdatePharmacyActivity extends AppCompatActivity {

    EditText ppName;
    TextView ppAddress, ppSerial, ppNumber;
    AppCompatButton savePharmacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pharmacy);

        ppName = findViewById(R.id.ppName);
        ppAddress = findViewById(R.id.ppAddress);
        ppNumber = findViewById(R.id.ppNumber);
        ppSerial = findViewById(R.id.ppSerial);
        savePharmacy = findViewById(R.id.savePharmacy);

        Intent intent = getIntent();
        int id = intent.getIntExtra("ph_id", 0);
        String name = intent.getStringExtra("Ph_Name");
        String number = intent.getStringExtra("phone_number");
        String address = intent.getStringExtra("Ph_Address");
        String serial = intent.getStringExtra("Ph_Serial");

        ppName.setHint(name);
        ppAddress.setText(address);
        ppNumber.setText(number);
        ppSerial.setText(serial);

        savePharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePharmacy(id, ppName.getText().toString());
            }
        });

    }

    public void updatePharmacy(int id, String name){

        StringRequest request = new StringRequest(Request.Method.POST, URLS.URL_UPDATE_PHARMACIES, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("error")){
                        Toast.makeText(UpdatePharmacyActivity.this, "response: " + object.getString("message"), Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(UpdatePharmacyActivity.this, "Response: " + object.getString("message"), Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }

                }catch (Exception exception){
                    Toast.makeText(UpdatePharmacyActivity.this, "Exception: " + exception, Toast.LENGTH_LONG).show();
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
                params.put("pharmace_name", name);
                return params;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);

    }

}