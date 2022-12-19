package com.example.kis.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kis.Adapters.ArztNotesAdapter;
import com.example.kis.Adapters.ArztPatientAdapter;
import com.example.kis.Database.DatabaseHelper;
import com.example.kis.R;

public class ArztPatientDetailsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Spinner spinner;
    CheckBox mrt, erledigt, bluttest;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arzt_patient_details);
        recyclerView = findViewById(R.id.Recycler);
        Intent intent = getIntent();
        int patientIdDetails = intent.getIntExtra(ArztPatientAdapter.EXTRA_NUMBER,0);

        databaseHelper = new DatabaseHelper(this);
        ArztNotesAdapter adapter = new ArztNotesAdapter(this,databaseHelper.getSpecificPatient(patientIdDetails),databaseHelper.getPatientEntry(patientIdDetails));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}