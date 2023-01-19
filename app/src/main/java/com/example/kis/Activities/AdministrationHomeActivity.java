package com.example.kis.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kis.Adapters.AdminstrationPatientAdapter;
import com.example.kis.Database.DatabaseHelper;
import com.example.kis.R;

public class AdministrationHomeActivity extends AppCompatActivity {
    Button logout, addPatient;
    static RecyclerView recyclerViewD;
    DatabaseHelper dataBaseHelper;
    SearchView svSearchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration_home);
        addPatient = findViewById(R.id.AdministrationHomeButtonAddPatient);
        logout = findViewById(R.id.AdministrationHomeButtonLogout);
         recyclerViewD = findViewById(R.id.AdministrationHomeRecyclerView);
        svSearchBar = findViewById(R.id.AdministrationHomeSearchView);

        dataBaseHelper = new DatabaseHelper(AdministrationHomeActivity.this);
        AdminstrationPatientAdapter adapter = new AdminstrationPatientAdapter(this,dataBaseHelper.getEveryPatient(),dataBaseHelper.getEveryEntry(),dataBaseHelper);
        recyclerViewD.setAdapter(adapter);
        recyclerViewD.setLayoutManager(new LinearLayoutManager(this));

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

        logout.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), StartActivity.class);
            startActivity(intent);
            finish();
        });
        addPatient.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AdministrationAddPatientActivity.class);
            startActivity(intent);
            finish();
        });
    }
}