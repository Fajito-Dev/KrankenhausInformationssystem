package com.example.kis.Adapters;

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
import com.example.kis.Models.EntryModel;
import com.example.kis.Models.PatientModel;
import com.example.kis.R;

import java.util.ArrayList;

public class ArztNotesAdapter extends RecyclerView.Adapter<ArztNotesAdapter.ArztNotesViewHolder> {
    public static final String EXTRA_NUMBER2 = "com.example.kis.Adapters.EXTRA_NUMBER2";
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

        //abfrage zu MRT/Blutergebnisse
        String documentEntry = "kein Dokument";
        if(entryModelList.get(position).isMrt()==true& entryModelList.get(position).isBloodtest()==true){
            documentEntry = "MRT & Blutwerte";
        }else if(entryModelList.get(position).isMrt()==true){
            documentEntry = "MRT";
        }else if(entryModelList.get(position).isBloodtest()==true){
            documentEntry = "Blutwerte";
        }

        String entryId = Integer.toString(entryModelList.get(position).getEintragId());

        holder.date.setText("22022022  #" + entryId);
        holder.note.setText(entryModelList.get(position).getNote());
        holder.state.setText(entryModelList.get(position).getCondition());
        holder.documents.setText(documentEntry);
        holder.imageButton.setImageResource(R.drawable.img);
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int entryIdDetails = entryModelList.get(position).getEintragId();
                Intent intent = new Intent(v.getContext(), ArztNotesDetailsActivity.class);
                intent.putExtra(EXTRA_NUMBER2,entryIdDetails);
                holder.imageButton.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return entryModelList.size();
    }

    public static class ArztNotesViewHolder extends RecyclerView.ViewHolder{
        TextView date, note, state, documents;
        ImageButton imageButton;

        public ArztNotesViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.ArztNotesCardDatum);
            note = itemView.findViewById(R.id.ArztNotesCardNote);
            state = itemView.findViewById(R.id.ArztNotesCardZustand);
            documents = itemView.findViewById(R.id.ArztNotesCardRequestedDocuments);
            imageButton = itemView.findViewById(R.id.ArztNotesCardDetailsIcon);
        }
    }
}
