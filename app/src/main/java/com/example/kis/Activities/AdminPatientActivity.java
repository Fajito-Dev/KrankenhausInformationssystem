package com.example.kis.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kis.Adapters.AdminPatientAdapter;
import com.example.kis.Database.DatabaseHelper;
import com.example.kis.Models.EntryModel;
import com.example.kis.Models.PatientModel;
import com.example.kis.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AdminPatientActivity extends AppCompatActivity {
    Button btnAddPatient, buttonAbort;
    DatabaseHelper dataBaseHelper;
    TextInputEditText edtPrename,edtName,edtPatientenId;
    TextView tvBday;
    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration_add_patient);
        btnAddPatient = findViewById(R.id.AdministrationAddPatientButton);
        edtPrename = findViewById(R.id.AdministrationAddPatientVornameInput);
        edtName = findViewById(R.id.AdministrationAddPatientNameInput);
        tvBday = findViewById(R.id.AdministrationAddPatientGeburtstaginput);
        edtPatientenId = findViewById(R.id.AdministrationAddPatientInsuranceNumberInput);
        dataBaseHelper = new DatabaseHelper(AdminPatientActivity.this);

        buttonAbort = findViewById(R.id.AdministrationAddPatientAbort);
        buttonAbort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), AdminHomeActivity.class);
                startActivity(intent);
                finish();
            }
        });


        tvBday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calender = Calendar.getInstance();
                final int year = calender.get(Calendar.YEAR);
                final int month = calender.get(Calendar.MONTH);
                final int day = calender.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AdminPatientActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,setListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month+1;
                String strDate = day+"/"+month+"/"+year;
                tvBday.setText(strDate);
            }
        };
        btnAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PatientModel patientModel;
                EntryModel entryModel = null;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(new Date());
                try {
                    patientModel = new PatientModel(Integer.parseInt(edtPatientenId.getText().toString()), edtPrename.getText().toString(), edtName.getText().toString(), tvBday.getText().toString());
                    entryModel = new EntryModel(0, Integer.parseInt(edtPatientenId.getText().toString()),date,dataBaseHelper.getFreeBed(),0,"ohneBefund",false, false,"Patient eingewiesen",0,0,0,"");
                    //Toast.makeText(AdminPatientActivity.this, patientModel.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(AdminPatientActivity.this, "error creating customer", Toast.LENGTH_SHORT).show();
                    patientModel = new PatientModel(0, "error", null, "error");
                }

                DatabaseHelper dataBaseHelper = new DatabaseHelper(AdminPatientActivity.this);
                dataBaseHelper.addPatient(patientModel);
                dataBaseHelper.addEntry(entryModel);

                AdminPatientAdapter adapter = new AdminPatientAdapter(AdminPatientActivity.this,dataBaseHelper.getEveryPatient(),dataBaseHelper.getEveryEntry(),dataBaseHelper);
                AdminHomeActivity.recyclerViewD.setAdapter(adapter);
                AdminHomeActivity.recyclerViewD.getAdapter().notifyDataSetChanged();

                Intent intent = new Intent(getApplicationContext(), AdminHomeActivity.class);
                startActivity(intent);
            }
        });
    }
}