package com.example.kis.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kis.Adapters.ArztPatientAdapter;
import com.example.kis.R;


public class ArztVisiteActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button buttonLogout;
    ImageButton buttonPatientDetails;
    String[] patientArr, patientBirthDates, patientDiagnoses;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arzt_visite);

        recyclerView = findViewById(R.id.recyclerView);
        patientArr = getResources().getStringArray(R.array.patientNames);
        patientBirthDates = getResources().getStringArray(R.array.patientBirthDates);
        patientDiagnoses = getResources().getStringArray(R.array.patientDiagnose);

        ArztPatientAdapter arztPatientAdapter = new ArztPatientAdapter(this,patientArr, patientBirthDates, patientDiagnoses);
        recyclerView.setAdapter(arztPatientAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}