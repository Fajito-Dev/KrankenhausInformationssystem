package com.example.kis.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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

public class LaborRequestsAdapter extends RecyclerView.Adapter<LaborRequestsAdapter.LaborRequestViewHolder> implements Filterable {
    public static final String EXTRA_NUMBER3 = "com.example.kis.Adapters.EXTRA_NUMBER3";
    Context context;
    ArrayList<PatientModel> patientModelList;
    ArrayList<EntryModel> entryModelList;
    ArrayList<PatientModel> patientModelListFull;

    public LaborRequestsAdapter(Context context, ArrayList<PatientModel> patientModelList,ArrayList<EntryModel> entryModelList) {
        this.context = context;
        this.patientModelList = patientModelList;
        this.entryModelList = entryModelList;
        patientModelListFull = new ArrayList<>(patientModelList);
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
        String bedNr = Integer.toString(entryModelList.get(position).getBedNr());
        String documentEntry = "kein Dokument";
        int posNr = 0;
        for(int i = 0;i<patientModelList.size();i++){
            if(patientModelList.get(i).getPatientId()==entryModelList.get(position).getPatientIde()){
                posNr = i;
            }
        }

        if(entryModelList.get(position).isMrt()==true& entryModelList.get(position).isBloodtest()==true){
            documentEntry = "MRT & Blutwerte";
        }else if(entryModelList.get(position).isMrt()==true){
            documentEntry = "MRT";
        }else if(entryModelList.get(position).isBloodtest()==true){
            documentEntry = "Blutwerte";
        }

        holder.tvName.setText(patientModelList.get(posNr).getPreName() + " " + patientModelList.get(posNr).getName());
        holder.tvNote.setText(entryModelList.get(position).getNote());
        holder.tvBedNr.setText("Bett " + bedNr);
        holder.tvDocuments.setText(documentEntry);
        holder.imageButton.setImageResource(R.drawable.img);
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int entryIdDetails = entryModelList.get(position).getEintragId();
                Intent intent = new Intent(v.getContext(), LaborPatientDetailsActivity.class);
                intent.putExtra(EXTRA_NUMBER3,entryIdDetails);
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
    public Filter getFilter(){
        return nameFilter;
    }
    public Filter nameFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<PatientModel> filteredList = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredList.addAll(patientModelListFull);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(PatientModel pm : patientModelListFull){
                    if(pm.getFullName().toLowerCase().contains(filterPattern)){
                        filteredList.add(pm);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            patientModelList.clear();
            patientModelList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };
}
