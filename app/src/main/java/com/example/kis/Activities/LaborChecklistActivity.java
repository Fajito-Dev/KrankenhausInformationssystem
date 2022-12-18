package com.example.kis.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kis.Adapters.LaborRequestsAdapter;
import com.example.kis.R;

public class LaborChecklistActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button logoutButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labor_checklist);

        LaborRequestsAdapter laborRequestsAdapter = new LaborRequestsAdapter(this);

        recyclerView = findViewById(R.id.LaborChecklistrecyclerView);
        logoutButton = findViewById(R.id.LaborChecklistButtonLogout);
    }

}