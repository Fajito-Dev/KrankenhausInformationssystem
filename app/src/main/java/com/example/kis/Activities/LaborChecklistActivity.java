package com.example.kis.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

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
    SearchView svSearchBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labor_checklist);
        recyclerView = findViewById(R.id.LaborChecklistrecyclerView);
        btnLogout = findViewById(R.id.LaborChecklistButtonLogout);
        svSearchBar = findViewById(R.id.search_bar2);


        databaseHelper = new DatabaseHelper(this);
        LaborRequestsAdapter adapter = new LaborRequestsAdapter(this,databaseHelper.getEveryPatient(),databaseHelper.getEntryLabor());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        svSearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
            }
        });
    }

}