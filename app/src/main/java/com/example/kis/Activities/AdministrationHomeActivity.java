package com.example.kis.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kis.R;

public class AdministrationHomeActivity extends AppCompatActivity {
    Button logout, addPatient;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration_home);

        logout = findViewById(R.id.AdministrationHomeButtonLogout);

        logout.setOnClickListener(v -> {
            finish();
        });

        addPatient = findViewById(R.id.AdministrationHomeButtonAddPatient);

        addPatient.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AdministrationAddPatientActivity.class);
            startActivity(intent);
            finish();
        });
    }
}