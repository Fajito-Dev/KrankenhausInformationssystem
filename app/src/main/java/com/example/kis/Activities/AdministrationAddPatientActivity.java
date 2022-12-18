package com.example.kis.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.kis.Database.DatabaseHelper;
import com.example.kis.Models.EntryModel;
import com.example.kis.Models.PatientModel;
import com.example.kis.R;
import com.google.android.material.textfield.TextInputEditText;

public class AdministrationAddPatientActivity extends AppCompatActivity {
    Button btnAddPatient;
    DatabaseHelper dataBaseHelper;
    TextInputEditText edtPrename,edtName,edtBirthdate,edtPatientenId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration_add_patient);
        btnAddPatient = findViewById(R.id.AdministrationAddPatientButton);
        edtPrename = findViewById(R.id.AdministrationAddPatientVornameInput);
        edtName = findViewById(R.id.AdministrationAddPatientNameInput);
        edtBirthdate = findViewById(R.id.AdministrationAddPatientGeburtstaginput);
        edtPatientenId = findViewById(R.id.AdministrationAddPatientInsuranceNumberInput);
        dataBaseHelper = new DatabaseHelper(AdministrationAddPatientActivity.this);

        btnAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PatientModel patientModel;
                EntryModel entryModel = null;
                //BettNr,Datum,eintragsId Autoincrement noch richtig machen
                try {
                    patientModel = new PatientModel(Integer.parseInt(edtPatientenId.getText().toString()), edtPrename.getText().toString(), edtName.getText().toString(), Integer.parseInt(edtBirthdate.getText().toString()));
                    entryModel = new EntryModel(0,Integer.parseInt(edtPatientenId.getText().toString()),0,0,false,"ohneBefund",false, false,"Patient eingewiesen",0,0,0);
                    Toast.makeText(AdministrationAddPatientActivity.this, patientModel.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) { //dat is schwachsinn :O
                    Toast.makeText(AdministrationAddPatientActivity.this, "error creating customer", Toast.LENGTH_SHORT).show();
                    patientModel = new PatientModel(0, "error", null, 0);
                }

                DatabaseHelper dataBaseHelper = new DatabaseHelper(AdministrationAddPatientActivity.this);
                boolean success = dataBaseHelper.addPatient(patientModel);
                boolean success2 = dataBaseHelper.addEntry(entryModel);
                Toast.makeText(AdministrationAddPatientActivity.this, "Success=" + success, Toast.LENGTH_SHORT).show();

                //startet login wieder
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
            }
        });
    }
}