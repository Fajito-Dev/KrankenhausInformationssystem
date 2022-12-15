package com.example.kis.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kis.Adapters.ArztPatientDetailsAdapter;
import com.example.kis.R;

public class ArztPatientDetailsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] patientArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_arzt_patienten_details);
        recyclerView = findViewById(R.id.Recycler);
        patientArr = getResources().getStringArray(R.array.patient_data);

        ArztPatientDetailsAdapter Adapter = new ArztPatientDetailsAdapter(this, patientArr);
        recyclerView.setAdapter(Adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}