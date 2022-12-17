package com.example.kis.Activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kis.Adapters.ArztNotesAdapter;
import com.example.kis.R;

public class ArztPatientDetailsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] dates, notes, painscala, states, docs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_arzt_patient_details);
        recyclerView = findViewById(R.id.Recycler);
        dates = getResources().getStringArray(R.array.notesDate);
        notes = getResources().getStringArray(R.array.notes);
        painscala = getResources().getStringArray(R.array.painscala);
        states = getResources().getStringArray(R.array.zustand);
        docs = getResources().getStringArray(R.array.LaborAnfrage);

        ArztNotesAdapter Adapter = new ArztNotesAdapter(this, dates, notes, painscala, states, docs);
        recyclerView.setAdapter(Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}