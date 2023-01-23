package com.example.kis.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kis.Adapters.LaborRequestsAdapter;
import com.example.kis.Database.DatabaseHelper;
import com.example.kis.Models.EntryModel;
import com.example.kis.Models.PatientModel;
import com.example.kis.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LaborPatientDetailsActivity extends AppCompatActivity {
    TextView tvName,tvAge,tvBed,tvDate,tvEntryId,tvDocument,tvNote;
    TextInputEditText tiedtLeukoNL,tiedtLymphoPercent,tiedtLypmhoAbsolut;
    DatabaseHelper databaseHelper;
    Button btnSafe,btnBack;
    String[] images = {"nopic", "mrt1", "mrt2"};
    String imageStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labor_patient_details);
        tvName = findViewById(R.id.LaborPatientDetailsName);
        tvAge = findViewById(R.id.LaborPatientDetailsAge);
        tvBed = findViewById(R.id.LaborPatientDetails);
        tvDate = findViewById(R.id.LaborPatientDetailsDate);
        tvEntryId = findViewById(R.id.LaborPatientDetailsEntryId);
        tvNote = findViewById(R.id.LaborPatientDetailsNotes);
        tvDocument = findViewById(R.id.LaborPatientDetailsRequestedDocuments);
        tiedtLeukoNL = findViewById(R.id.laborPatientDetailsLeukoValue);
        tiedtLymphoPercent = findViewById(R.id.laborPatientDetailsLymphoLeukoRatioValue);
        tiedtLypmhoAbsolut = findViewById(R.id.LaborPatientDetailsLymphoLeukoRatioValue);
        btnSafe = findViewById(R.id.LaborPatientDetailsButtonSubmit);
        btnBack = findViewById(R.id.LaborPatientDetailsAbort);
        final ImageView imageView = findViewById(R.id.LaborPatientDetailsMRTPreview);

        databaseHelper = new DatabaseHelper(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, images);
        Spinner spinner = findViewById(R.id.LaborPatientDetailsSpinnerMRT);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected image name
                String imageName = images[position];
                imageStr = imageName;
                // Get the resource ID of the image
                int resId = getResources().getIdentifier(imageName, "drawable", getPackageName());
                // Set the image on the ImageView
                imageView.setImageResource(resId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        Intent intent = getIntent();
        int entryId = intent.getIntExtra(LaborRequestsAdapter.EXTRA_NUMBER3,0);

        PatientModel patientModel = databaseHelper.getSpecificPatientModel(databaseHelper.getSpecificEntryModelEntryId(entryId).getPatientIde());
        EntryModel entryModel = databaseHelper.getSpecificEntryModelEntryId(entryId);

        String strBed = Integer.toString(databaseHelper.getSpecificEntryModelEntryId(entryId).getBedNr());
        String strEntryId = Integer.toString(entryId);

        String documentEntry = "keine Angabe FEHLER";
        if(databaseHelper.getSpecificEntryModelEntryId(entryId).isMrt()==true& databaseHelper.getSpecificEntryModelEntryId(entryId).isBloodtest()==true){
            documentEntry = "Auftrag MRT & Blutwerte";
        }else if(databaseHelper.getSpecificEntryModelEntryId(entryId).isMrt()==true){
            documentEntry = "Auftrag MRT";
        }else if(databaseHelper.getSpecificEntryModelEntryId(entryId).isBloodtest()==true){
            documentEntry = "Auftrag Blutwerte";
        }
        // kann jetzt ueberarbeitet werden wegen entry und patientModel
        tvName.setText(patientModel.getPreName() + " " + patientModel.getName());
        tvAge.setText("Alter " + patientModel.getBirthDate()+" "+ "("+patientModel.getAge()+")");
        tvBed.setText("Bett " + strBed);
        tvDate.setText("Datum " + databaseHelper.getSpecificEntryModelEntryId(entryId).getDate());
        tvEntryId.setText("#" + strEntryId);
        tvNote.setText(databaseHelper.getSpecificEntryModelEntryId(entryId).getNote());
        tvDocument.setText(documentEntry);
        DatabaseHelper dataBaseHelper = new DatabaseHelper(LaborPatientDetailsActivity.this);

        btnSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EntryModel entryModel = databaseHelper.getSpecificEntryModelEntryId(entryId);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(new Date());
                EntryModel entryModelNew = new EntryModel();
                try {
                    // bei condition kommt condition.toString() rein aber erst wenn Spinner gesetzt ist
                    entryModelNew = new EntryModel(0,entryModel.getPatientIde(),date,entryModel.getBedNr(),0,"kein Zustand",false, false,"Auftrag bearbeitet",Integer.parseInt(tiedtLeukoNL.getText().toString()),Integer.parseInt(tiedtLymphoPercent.getText().toString()),Integer.parseInt(tiedtLypmhoAbsolut.getText().toString()), imageStr);
                    Toast.makeText(LaborPatientDetailsActivity.this, entryModel.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) { //dat is schwachsinn :O
                    Toast.makeText(LaborPatientDetailsActivity.this, "error creating customer", Toast.LENGTH_SHORT).show();
                }
                boolean success = dataBaseHelper.addEntry(entryModelNew);
                Toast.makeText(LaborPatientDetailsActivity.this, "Success=" + success, Toast.LENGTH_SHORT).show();

                EntryModel entryModel2 = databaseHelper.getSpecificEntryModelEntryId(entryId);
                entryModel2.setVisited(2);
                dataBaseHelper.updateEntry(entryModel2);

                Intent intent = new Intent(getApplicationContext(), LaborChecklistActivity.class);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}