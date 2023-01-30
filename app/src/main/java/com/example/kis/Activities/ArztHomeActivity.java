package com.example.kis.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kis.Adapters.ArztPatientAdapter;
import com.example.kis.Database.DatabaseHelper;
import com.example.kis.R;


public class ArztHomeActivity extends AppCompatActivity {
    DatabaseHelper dataBaseHelper;
    static RecyclerView recyclerViewA;
    Button buttonLogout;
    SearchView svSearchBar;
    ToggleButton toggleButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arzt_visite);
        recyclerViewA = findViewById(R.id.recyclerView);
        buttonLogout = findViewById(R.id.ArztVisiteButtonLogout);
        svSearchBar = findViewById(R.id.search_bar);
        toggleButton = findViewById(R.id.ArztVisiteFilterButton);

        dataBaseHelper = new DatabaseHelper(ArztHomeActivity.this);
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

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ArztPatientAdapter adapter = new ArztPatientAdapter(ArztHomeActivity.this,dataBaseHelper.getEveryPatientVIsit(dataBaseHelper.getEveryEntry()),dataBaseHelper.getEveryEntry());
                    adapter.notifyDataSetChanged();
                    recyclerViewA.setAdapter(adapter);
                    recyclerViewA.setLayoutManager(new LinearLayoutManager(ArztHomeActivity.this));
                } else {
                    ArztPatientAdapter adapter = new ArztPatientAdapter(ArztHomeActivity.this,dataBaseHelper.getEveryPatientBed(dataBaseHelper.getEveryEntry()),dataBaseHelper.getEveryEntry());
                    adapter.notifyDataSetChanged();
                    recyclerViewA.setAdapter(adapter);
                    recyclerViewA.setLayoutManager(new LinearLayoutManager(ArztHomeActivity.this));
                }
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}