package com.example.kis.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kis.R;

public class StartActivity extends AppCompatActivity {

    Button buttonDoctor,buttonAdministration,buttonLaboratory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        buttonDoctor = findViewById(R.id.ButtonArztHome);
        buttonLaboratory = findViewById(R.id.ButtonLabor);
        buttonAdministration = findViewById(R.id.ButtonVerwaltung);

        buttonDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ArztVisiteActivity.class);
                startActivity(intent);
            }
        });
        buttonLaboratory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LaborChecklistActivity.class);
                startActivity(intent);
            }
        });
    }
}