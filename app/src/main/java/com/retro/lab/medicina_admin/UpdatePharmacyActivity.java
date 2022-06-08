package com.retro.lab.medicina_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;

public class UpdatePharmacyActivity extends AppCompatActivity {

    EditText ppName;
    TextView ppAddress, ppSerial, ppNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pharmacy);

        ppName = findViewById(R.id.ppName);
        ppAddress = findViewById(R.id.ppAddress);
        ppSerial = findViewById(R.id.ppSerial);


        Intent intent = getIntent();
        String name = intent.getStringExtra("Ph_Name");
        String address = intent.getStringExtra("Ph_Address");
        String serial = intent.getStringExtra("Ph_Serial");

        ppName.setHint(name);
        ppAddress.setText(address);
        ppSerial.setText(serial);


    }

    public void updatePharmacy(){

    }

}