package com.example.kis.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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
import com.example.kis.Models.PatientModel;
import com.example.kis.R;

public class ArztPatientDetailsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Spinner condition;
    CheckBox mrt, blodtest, visit;
    TextView name,age, bednr;
    DatabaseHelper databaseHelper;
    EditText note;
    Button btnSafe,btnBack;

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

        Intent intent = getIntent();
        int patientIdDetails = intent.getIntExtra(ArztPatientAdapter.EXTRA_NUMBER,0);
        databaseHelper = new DatabaseHelper(this);
        ArztNotesAdapter adapter = new ArztNotesAdapter(this,databaseHelper.getSpecificPatient(patientIdDetails),databaseHelper.getPatientEntry(patientIdDetails));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int bedNr = databaseHelper.getSpecificEntryModel(patientIdDetails).getBedNr();
        String bedNrS = Integer.toString(bedNr);

        name.setText(databaseHelper.getSpecificPatientModel(patientIdDetails).getPreName() + " " + databaseHelper.getSpecificPatientModel(patientIdDetails).getName());
        age.setText(databaseHelper.getSpecificPatientModel(patientIdDetails).getBirthDateS());
        bednr.setText("Bett " + bedNrS);

        // Mit Button Safe Eintrag erstellen
        btnSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EntryModel entryModel = null;
                try {
                    // bei condition kommt condition.toString() rein aber erst wenn Spinner gesetzt ist
                    int visitNr = 0;
                    if(visit.isChecked()==true){
                        visitNr = 1;
                    }
                    entryModel = new EntryModel(0,patientIdDetails,0,bedNr,visitNr,"kein Zustand",mrt.isChecked(), blodtest.isChecked(),note.getText().toString(),0,0,0);
                    Toast.makeText(ArztPatientDetailsActivity.this, entryModel.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception e) { //dat is schwachsinn :O
                    Toast.makeText(ArztPatientDetailsActivity.this, "error creating customer", Toast.LENGTH_SHORT).show();
                }
                DatabaseHelper dataBaseHelper = new DatabaseHelper(ArztPatientDetailsActivity.this);
                boolean success = dataBaseHelper.addEntry(entryModel);
                Toast.makeText(ArztPatientDetailsActivity.this, "Success=" + success, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), ArztVisiteActivity.class);
                startActivity(intent);
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
}