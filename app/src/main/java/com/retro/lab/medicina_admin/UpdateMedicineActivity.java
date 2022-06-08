package com.retro.lab.medicina_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateMedicineActivity extends AppCompatActivity {

    EditText medName, meddPrice;
    TextView phhname, phhAddress, meddSerial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_medicine);

        medName = findViewById(R.id.meddName);
        meddPrice = findViewById(R.id.meddPrice);

        phhname = findViewById(R.id.phhName);
        phhAddress = findViewById(R.id.phhAddress);
        meddSerial = findViewById(R.id.meddSerial);

        Intent intent = getIntent();
        String medicineName = intent.getStringExtra("Med_Name");
        String medicinePrice = intent.getStringExtra("Med_price");
        String pharmacyName = intent.getStringExtra("Ph_Name");
        String pharmacyAddresss = intent.getStringExtra("Ph_Address");
        String medicineSerial = intent.getStringExtra("Med_Serial");

        medName.setHint(medicineName);
        meddPrice.setHint(medicinePrice);

        phhname.setText(pharmacyName);
        phhAddress.setText(pharmacyAddresss);
        meddSerial.setText(medicineSerial);

    }

    public void updateMedicine(){

    }

}