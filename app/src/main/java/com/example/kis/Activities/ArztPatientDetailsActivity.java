package com.example.kis.Activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kis.Adapters.ArztNotesAdapter;
import com.example.kis.R;

public class ArztPatientDetailsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] patientArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_arzt_patienten_details);
        recyclerView = findViewById(R.id.Recycler);
        patientArr = getResources().getStringArray(R.array.patientNames);

        ArztNotesAdapter Adapter = new ArztNotesAdapter(this, patientArr);
        recyclerView.setAdapter(Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}