package com.example.kis.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kis.Adapters.ArztPatientAdapter;
import com.example.kis.Database.DatabaseHelper;
import com.example.kis.R;


public class ArztVisiteActivity extends AppCompatActivity {
    DatabaseHelper dataBaseHelper;
    RecyclerView recyclerViewA;
    Button buttonLogout;
    ImageButton buttonPatientDetails;
    SearchView svSearchBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arzt_visite);
        recyclerViewA = findViewById(R.id.recyclerView);
        buttonLogout = findViewById(R.id.ArztVisiteButtonLogout);
        svSearchBar = findViewById(R.id.search_bar);

        dataBaseHelper = new DatabaseHelper(ArztVisiteActivity.this);
        ArztPatientAdapter adapter = new ArztPatientAdapter(this,dataBaseHelper.getEveryPatientBed(dataBaseHelper.getEveryEntry()),dataBaseHelper.getEveryEntry());
        recyclerViewA.setAdapter(adapter);
        recyclerViewA.setLayoutManager(new LinearLayoutManager(this));



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

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StartActivity.class);
                startActivity(intent);
            }
        });
    }
}