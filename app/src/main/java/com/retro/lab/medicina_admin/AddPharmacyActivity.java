package com.retro.lab.medicina_admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
                addPharmacy();
            }
        });

    }

    private void addPharmacy() {

        onBackPressed();
    }
}