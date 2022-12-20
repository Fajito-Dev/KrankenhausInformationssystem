package com.example.kis.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kis.Adapters.LaborRequestsAdapter;
import com.example.kis.Database.DatabaseHelper;
import com.example.kis.R;

public class LaborChecklistActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button logoutButton;
    DatabaseHelper databaseHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labor_checklist);

        databaseHelper = new DatabaseHelper(this);
        LaborRequestsAdapter laborRequestsAdapter = new LaborRequestsAdapter(this,databaseHelper.getEveryPatient(),databaseHelper.getEntryLabor());

        recyclerView = findViewById(R.id.LaborChecklistrecyclerView);
        logoutButton = findViewById(R.id.LaborChecklistButtonLogout);
    }

}