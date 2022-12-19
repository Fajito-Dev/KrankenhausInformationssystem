package com.example.kis.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kis.Models.EntryModel;
import com.example.kis.Models.PatientModel;
import com.example.kis.R;

import java.util.ArrayList;

public class ArztNotesAdapter extends RecyclerView.Adapter<ArztNotesAdapter.ArztNotesViewHolder> {

    Context context;
    ArrayList<PatientModel> patientModelList;
    ArrayList<EntryModel> entryModelList;

    public ArztNotesAdapter(Context context, ArrayList<PatientModel> patientModelList,ArrayList<EntryModel> entryModelList) {
        this.context = context;
        this.patientModelList = patientModelList;
        this.entryModelList = entryModelList;
    }

    @NonNull
    @Override
    public ArztNotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.arzt_notes_row, parent, false);
        return new ArztNotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArztNotesViewHolder holder, int position) {
       // holder.date.setText(entryModelList.get(position).getDate());
        holder.date.setText("22022022");
        holder.note.setText(entryModelList.get(position).getNote());
        holder.state.setText(entryModelList.get(position).getCondition());

        holder.painscale.setText("TEST 1");
        holder.state.setText("TEST 2");
        holder.documents.setText("TEST 3");

    }

    @Override
    public int getItemCount() {
        return entryModelList.size();
    }

    public static class ArztNotesViewHolder extends RecyclerView.ViewHolder{
        TextView date, note, painscale, state, documents;

        public ArztNotesViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.ArztNotesCardDatum);
            note = itemView.findViewById(R.id.ArztNotesCardNote);
            painscale = itemView.findViewById(R.id.ArztNotesCardSchmerzensgrad);
            state = itemView.findViewById(R.id.ArztNotesCardZustand);
            documents = itemView.findViewById(R.id.ArztNotesCardRequestedDocuments);

        }
    }
}
