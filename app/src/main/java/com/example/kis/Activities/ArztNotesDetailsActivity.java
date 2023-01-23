package com.example.kis.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kis.Adapters.ArztNotesAdapter;
import com.example.kis.Database.DatabaseHelper;
import com.example.kis.R;

public class ArztNotesDetailsActivity extends AppCompatActivity {
    TextView tvEntryDate,tvEntryId,tvEntryNote,tvEntryLeukoNl,tvEntryLymphoPercent,tvEntryLymphoAbsolut;
    ImageView imgvEntryMrt;
    DatabaseHelper databaseHelper;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arzt_notes_details);
        tvEntryDate = findViewById(R.id.ArztNotesDetailsDate);
        tvEntryId = findViewById(R.id.ArztNotesDetailsEntryID);
        tvEntryNote = findViewById(R.id.ArztNotesDetailNotesLongText);
        tvEntryLeukoNl = findViewById(R.id.ArztNotesDetailsLeukoValue);
        tvEntryLymphoPercent = findViewById(R.id.ArztNotesDetailsLymphoValue);
        tvEntryLymphoAbsolut = findViewById(R.id.ArztNotesDetailsLymphoAbsolutValue);
        databaseHelper = new DatabaseHelper(this);
        btnBack = findViewById(R.id.ArztNotesDetailsBackButton);
        //imgvEntryMrt.findViewById(R.id.ArztNotesDetailsImageViewMRT);


        //TODO: MUSS AUS DATENBANK GEGETTET WERDEN
       // imgvEntryMrt.setImageResource(R.drawable.mrt1);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent intent = getIntent();
        int entryId = intent.getIntExtra(ArztNotesAdapter.EXTRA_NUMBER2,0);



        String enryIdTxt = Integer.toString(databaseHelper.getSpecificEntryModelEntryId(entryId).getEintragId());

        String entryLeukoNl = Float.toString(databaseHelper.getSpecificEntryModelEntryId(entryId).getLeukoNl());
        String entryLypmhoPercent = Float.toString(databaseHelper.getSpecificEntryModelEntryId(entryId).getLymphoProzent());
        String entryLymphoAbsolut = Float.toString(databaseHelper.getSpecificEntryModelEntryId(entryId).getLymphoAbsolut());

        tvEntryDate.setText(databaseHelper.getSpecificEntryModelEntryId(entryId).getDate());
        tvEntryId.setText("#" + enryIdTxt + "v="+databaseHelper.getSpecificEntryModelEntryId(entryId).getVisited());
        tvEntryNote.setText(databaseHelper.getSpecificEntryModelEntryId(entryId).getNote());
        tvEntryLeukoNl.setText(entryLeukoNl);
        tvEntryLymphoPercent.setText(entryLypmhoPercent);
        tvEntryLymphoAbsolut.setText(entryLymphoAbsolut);
    }
}