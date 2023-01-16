package com.example.kis.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kis.Adapters.ArztNotesAdapter;
import com.example.kis.Adapters.ArztPatientAdapter;
import com.example.kis.Database.DatabaseHelper;
import com.example.kis.Models.EntryModel;
import com.example.kis.R;
import com.google.android.material.textview.MaterialTextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ArztPatientDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    RecyclerView recyclerView;
    Spinner condition;
    CheckBox mrt, blodtest, visit;
    TextView name,age, bednr;
    DatabaseHelper databaseHelper;
    MaterialTextView note;
    Button btnSafe,btnBack;
    String textSpinner;
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arzt_patient_details);
        recyclerView = findViewById(R.id.Recycler);
        condition = findViewById(R.id.ArztNotesSpinnerZustand);
        mrt = findViewById(R.id.ArztNotesCheckBoxMRT);
        blodtest = findViewById(R.id.ArztNotesCheckBoxBlutErgebnis);
        visit = findViewById(R.id.ArztNotesCheckBoxVisiteDone);
        name = findViewById(R.id.ArztNotesPatientName);
        age = findViewById(R.id.ArztNotesGeburtstag);
        bednr = findViewById(R.id.ArztNotesBettNr);
        note = findViewById(R.id.Notes);
        btnSafe = findViewById(R.id.ArztNotesSaveButton);
        btnBack = findViewById(R.id.ArztNotesButtonVerwerfen);
        backButton = findViewById(R.id.ArztNotesBackButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), ArztVisiteActivity.class);
                startActivity(intent);
            }
        });

        ArrayAdapter<CharSequence> adapterB = ArrayAdapter.createFromResource(this, R.array.zustand, android.R.layout.simple_spinner_item);
        adapterB.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        condition.setAdapter(adapterB);
        condition.setOnItemSelectedListener(this);

        Intent intent = getIntent();
        int patientIdDetails = intent.getIntExtra(ArztPatientAdapter.EXTRA_NUMBER,0);
        databaseHelper = new DatabaseHelper(this);
        ArztNotesAdapter adapter = new ArztNotesAdapter(this,databaseHelper.getSpecificPatient(patientIdDetails),databaseHelper.getPatientEntry(patientIdDetails));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int bedNr = databaseHelper.getSpecificEntryModel(patientIdDetails).getBedNr();
        String bedNrS = Integer.toString(bedNr);

        name.setText(databaseHelper.getSpecificPatientModel(patientIdDetails).getPreName() + " " + databaseHelper.getSpecificPatientModel(patientIdDetails).getName());
        age.setText(databaseHelper.getSpecificPatientModel(patientIdDetails).getBirthDate());
        bednr.setText("Bett " + bedNrS);

        // Mit Button Safe Eintrag erstellen
        btnSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EntryModel entryModel = null;

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(new Date());
                String strGesund = "gesund";
                String strTextSpinner = textSpinner;
                try {
                    // bei condition kommt condition.toString() rein aber erst wenn Spinner gesetzt ist
                    int visitNr = 0;
                    if(visit.isChecked()==true){
                        visitNr = 1;
                    }
                    if(textSpinner.equals("gesund")){
                        entryModel = new EntryModel(0,patientIdDetails,date,0,visitNr,textSpinner,mrt.isChecked(), blodtest.isChecked(),note.getText().toString(),0,0,0,"");

                    }else{
                        entryModel = new EntryModel(0,patientIdDetails,date,bedNr,visitNr,textSpinner,mrt.isChecked(), blodtest.isChecked(),note.getText().toString(),0,0,0,"");
                    }
                    Toast.makeText(ArztPatientDetailsActivity.this, entryModel.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) { //dat is schwachsinn :O
                    Toast.makeText(ArztPatientDetailsActivity.this, "error creating customer", Toast.LENGTH_SHORT).show();
                }
                DatabaseHelper dataBaseHelper = new DatabaseHelper(ArztPatientDetailsActivity.this);
                boolean success = dataBaseHelper.addEntry(entryModel);
                Toast.makeText(ArztPatientDetailsActivity.this, "Success=" + success, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ArztVisiteActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         textSpinner = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}