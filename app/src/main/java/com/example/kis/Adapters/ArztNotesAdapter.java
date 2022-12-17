package com.example.kis.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kis.R;

public class ArztNotesAdapter extends RecyclerView.Adapter<ArztNotesAdapter.ArztNotesViewHolder> {

    String[] dates, notes, states, painscala, documents;
    Context context;

    public ArztNotesAdapter(Context cn, String[] d, String[] n, String[] s, String[] ps,String[] doc) {
        context = cn;
        dates = d;
        notes = n;
        states = s;
        painscala = ps;
        documents = doc;
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
        holder.date.setText(dates[position]);
        holder.note.setText(notes[position]);
        holder.painscale.setText(painscala[position]);
        holder.state.setText(states[position]);
        holder.documents.setText(documents[position]);
    }

    @Override
    public int getItemCount() {
        return dates.length;
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
