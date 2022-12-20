package com.example.kis.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kis.Activities.ArztNotesDetailsActivity;
import com.example.kis.Activities.LaborChecklistActivity;
import com.example.kis.Activities.LaborPatientDetailsActivity;
import com.example.kis.Models.EntryModel;
import com.example.kis.Models.PatientModel;
import com.example.kis.R;

import java.util.ArrayList;

public class LaborRequestsAdapter extends RecyclerView.Adapter<LaborRequestsAdapter.LaborRequestViewHolder> {
    Context context;
    ArrayList<PatientModel> patientModelList;
    ArrayList<EntryModel> entryModelList;

    public LaborRequestsAdapter(Context context, ArrayList<PatientModel> patientModelList,ArrayList<EntryModel> entryModelList) {
        this.context = context;
        this.patientModelList = patientModelList;
        this.entryModelList = entryModelList;
    }

    @NonNull
    @Override
    public LaborRequestsAdapter.LaborRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.labor_patient_card, parent, false);
        return new LaborRequestViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull LaborRequestsAdapter.LaborRequestViewHolder holder, @SuppressLint("RecyclerView") int position) {
        int posNr = 0;
        for(int i = 0;i<patientModelList.size();i++){
            if(patientModelList.get(i).getPatientId()==entryModelList.get(position).getPatientIde()){
                posNr = i;
            }
        }


        //abfrage zu MRT/Blutergebnisse
        String documentEntry = "kein Dokument";
        if(entryModelList.get(position).isMrt()==true& entryModelList.get(position).isBloodtest()==true){
            documentEntry = "MRT & Blutwerte";
        }else if(entryModelList.get(position).isMrt()==true){
            documentEntry = "MRT";
        }else if(entryModelList.get(position).isBloodtest()==true){
            documentEntry = "Blutwerte";
        }

        String bedNr = Integer.toString(entryModelList.get(position).getBedNr());

        holder.tvName.setText(patientModelList.get(posNr).getPreName() + " " + patientModelList.get(posNr).getName());
        holder.tvNote.setText(entryModelList.get(position).getNote());
        holder.tvBedNr.setText("Bett " + bedNr);
        holder.tvDocuments.setText(documentEntry);

        holder.imageButton.setImageResource(R.drawable.img);
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LaborPatientDetailsActivity.class);
                holder.imageButton.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return entryModelList.size();
    }

    public class LaborRequestViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvNote, tvBedNr, tvDocuments;
        ImageButton imageButton;
        public LaborRequestViewHolder(@NonNull View itemView) {

            super(itemView);
            tvName = itemView.findViewById(R.id.LaborPatientCardName);
            tvNote = itemView.findViewById(R.id.LaborPatientCardNotes);
            tvBedNr = itemView.findViewById(R.id.LaborPatientCardBettNummer);
            tvDocuments = itemView.findViewById(R.id.LaborPatientCardRequestedDocuments);
            imageButton = itemView.findViewById(R.id.LaborPatientCardDetailsIcon);
        }
    }

}
