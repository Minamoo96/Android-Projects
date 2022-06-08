package com.retro.lab.medicina_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddMedicineActivity extends AppCompatActivity {

    EditText medName, phName, medPrice, phAddress, medSerial;
    AppCompatButton saveMedicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        medName = findViewById(R.id.medName);
        phName = findViewById(R.id.phName);
        medPrice = findViewById(R.id.medPrice);
        phAddress = findViewById(R.id.phAddress);
        medSerial = findViewById(R.id.medSerial);

        saveMedicine = findViewById(R.id.saveMedicine);
        saveMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMedicine();
            }
        });

    }

    private void addMedicine() {

        onBackPressed();
    }
}