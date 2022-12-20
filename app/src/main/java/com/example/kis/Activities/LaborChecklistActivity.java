package com.example.kis.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kis.Adapters.LaborRequestsAdapter;
import com.example.kis.Database.DatabaseHelper;
import com.example.kis.R;

public class LaborChecklistActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btnLogout;
    DatabaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labor_checklist);
        recyclerView = findViewById(R.id.LaborChecklistrecyclerView);
        btnLogout = findViewById(R.id.LaborChecklistButtonLogout);

        databaseHelper = new DatabaseHelper(this);
        LaborRequestsAdapter laborRequestsAdapter = new LaborRequestsAdapter(this,databaseHelper.getEveryPatient(),databaseHelper.getEntryLabor());
        recyclerView.setAdapter(laborRequestsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
            }
        });
    }

}