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

import com.example.kis.Activities.ArztEntryActivity;
import com.example.kis.Models.EntryModel;
import com.example.kis.Models.PatientModel;
import com.example.kis.R;

import java.util.ArrayList;

public class ArztEntryAdapter extends RecyclerView.Adapter<ArztEntryAdapter.ArztNotesViewHolder> {
    public static final String EXTRA_NUMBER2 = "com.example.kis.Adapters.EXTRA_NUMBER2";
    Context context;
    ArrayList<PatientModel> patientModelList;
    ArrayList<EntryModel> entryModelList;

    public ArztEntryAdapter(Context context, ArrayList<PatientModel> patientModelList, ArrayList<EntryModel> entryModelList) {
        this.context = context;
        this.patientModelList = patientModelList;
        this.entryModelList = entryModelList;
    }

    @NonNull
    @Override
    public ArztNotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.arzt_notes_card, parent, false);
        return new ArztNotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArztNotesViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String documentEntry = "kein Auftrag";

        if(entryModelList.get(position).isMrt() & entryModelList.get(position).isBloodtest()==true){
            documentEntry = "MRT & Blutwerte";
        }else if(entryModelList.get(position).isMrt()){
            documentEntry = "MRT";
        }else if(entryModelList.get(position).isBloodtest()){
            documentEntry = "Blutwerte";
        }

        holder.date.setText(entryModelList.get(position).getDate());
        holder.note.setText(entryModelList.get(position).getNote());
        holder.state.setText(entryModelList.get(position).getCondition());
        holder.documents.setText(documentEntry);
        holder.imgbtnIcon.setImageResource(R.drawable.img);
        holder.imgbtnIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int entryIdDetails = entryModelList.get(position).getEintragId();
                Intent intent = new Intent(v.getContext(), ArztEntryActivity.class);
                intent.putExtra(EXTRA_NUMBER2,entryIdDetails);
                holder.imgbtnIcon.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return entryModelList.size();
    }

    public static class ArztNotesViewHolder extends RecyclerView.ViewHolder{
        TextView date, note, state, documents;
        ImageButton imgbtnIcon;

        public ArztNotesViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.ArztNotesCardDatum);
            note = itemView.findViewById(R.id.ArztNotesCardNote);
            state = itemView.findViewById(R.id.ArztNotesCardZustand);
            documents = itemView.findViewById(R.id.ArztNotesCardRequestedDocuments);
            imgbtnIcon = itemView.findViewById(R.id.ArztNotesCardDetailsIcon);
        }
    }
}
