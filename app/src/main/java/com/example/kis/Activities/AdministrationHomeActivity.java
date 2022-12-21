package com.example.kis.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kis.Adapters.AdminstrationPatientAdapter;
import com.example.kis.Database.DatabaseHelper;
import com.example.kis.R;

public class AdministrationHomeActivity extends AppCompatActivity {
    Button logout, addPatient;
    RecyclerView recyclerView;
    DatabaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration_home);
        addPatient = findViewById(R.id.AdministrationHomeButtonAddPatient);
        logout = findViewById(R.id.AdministrationHomeButtonLogout);
        recyclerView = findViewById(R.id.AdministrationHomeRecyclerView);

        dataBaseHelper = new DatabaseHelper(AdministrationHomeActivity.this);
        AdminstrationPatientAdapter adapter = new AdminstrationPatientAdapter(this,dataBaseHelper.getEveryPatient(),dataBaseHelper.getEveryEntry());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        logout.setOnClickListener(v -> {
            finish();
        });
        addPatient.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AdministrationAddPatientActivity.class);
            startActivity(intent);
            finish();
        });
    }
}