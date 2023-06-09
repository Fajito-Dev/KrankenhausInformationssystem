package com.example.kis.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kis.Adapters.ArztEntryAdapter;
import com.example.kis.Adapters.ArztPatientAdapter;
import com.example.kis.Database.DatabaseHelper;
import com.example.kis.Models.EntryModel;
import com.example.kis.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ArztPatientActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    RecyclerView recyclerView;
    Spinner condition;
    CheckBox mrt, blodtest;
    TextView name,age, bednr;
    DatabaseHelper databaseHelper;
    TextInputEditText note;
    Button btnSafe,btnBack;
    String textSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arzt_patient_details);
        recyclerView = findViewById(R.id.Recycler);
        condition = findViewById(R.id.ArztNotesSpinnerZustand);
        mrt = findViewById(R.id.ArztNotesCheckBoxMRT);
        blodtest = findViewById(R.id.ArztNotesCheckBoxBlutErgebnis);
        name = findViewById(R.id.ArztNotesPatientName);
        age = findViewById(R.id.ArztNotesGeburtstag);
        bednr = findViewById(R.id.ArztNotesBettNr);
        note = findViewById(R.id.Notes);
        btnSafe = findViewById(R.id.ArztNotesSaveButton);
        btnBack = findViewById(R.id.ArztNotesButtonVerwerfen);

        ArrayAdapter<CharSequence> adapterB = ArrayAdapter.createFromResource(this, R.array.zustand, android.R.layout.simple_spinner_item);
        adapterB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        condition.setAdapter(adapterB);
        condition.setOnItemSelectedListener(this);

        Intent intent = getIntent();
        int patientIdDetails = intent.getIntExtra(ArztPatientAdapter.EXTRA_NUMBER,0);
        databaseHelper = new DatabaseHelper(this);
        ArztEntryAdapter adapter = new ArztEntryAdapter(this,databaseHelper.getSpecificPatient(patientIdDetails),databaseHelper.getPatientEntry(patientIdDetails));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int bedNr = databaseHelper.getSpecificEntryModel(patientIdDetails).getBedNr();
        String bedNrS = Integer.toString(bedNr);

        name.setText(databaseHelper.getSpecificPatientModel(patientIdDetails).getPreName() + " " + databaseHelper.getSpecificPatientModel(patientIdDetails).getName());
        age.setText(databaseHelper.getSpecificPatientModel(patientIdDetails).getBirthDate()+" "+ "("+databaseHelper.getSpecificPatientModel(patientIdDetails).getAge()+")");
        bednr.setText("Bett " + bedNrS);

        btnSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EntryModel entryModel = null;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(new Date());

                try {
                    int visitNr = 0;
                    if(textSpinner.equals("gesund")){
                        entryModel = new EntryModel(0,patientIdDetails,date,0,visitNr,textSpinner,mrt.isChecked(), blodtest.isChecked(),note.getText().toString(),0,0,0,"");
                    }else{
                        entryModel = new EntryModel(0,patientIdDetails,date,bedNr,visitNr,textSpinner,mrt.isChecked(), blodtest.isChecked(),note.getText().toString(),0,0,0,"");
                    }
                    Toast.makeText(ArztPatientActivity.this, entryModel.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(ArztPatientActivity.this, "error creating customer", Toast.LENGTH_SHORT).show();
                }

                DatabaseHelper dataBaseHelper = new DatabaseHelper(ArztPatientActivity.this);
                dataBaseHelper.addEntry(entryModel);
                ArztPatientAdapter aPadapter = new ArztPatientAdapter(ArztPatientActivity.this,databaseHelper.getEveryPatientBed(databaseHelper.getEveryEntry()),databaseHelper.getEveryEntry());
                ArztHomeActivity.recyclerViewA.setAdapter(aPadapter);
                ArztHomeActivity.recyclerViewA.getAdapter().notifyDataSetChanged();
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ArztHomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         textSpinner = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}