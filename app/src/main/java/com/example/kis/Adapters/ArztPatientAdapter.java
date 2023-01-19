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

import com.example.kis.Activities.ArztPatientDetailsActivity;
import com.example.kis.Activities.ArztNotesDetailsActivity;
import com.example.kis.Models.EntryModel;
import com.example.kis.Models.PatientModel;
import com.example.kis.R;

import java.util.ArrayList;

public class ArztPatientAdapter extends RecyclerView.Adapter<ArztPatientAdapter.ArztPatientViewHolder> implements Filterable {
    public static final String EXTRA_NUMBER = "com.example.kis.Adapters.EXTRA_NUMBER";
    Context context;
    ArrayList<PatientModel> patientModelList;
    ArrayList<EntryModel> entryModelList;
    ArrayList<PatientModel> patientModelListFull;

    public ArztPatientAdapter(Context context, ArrayList<PatientModel> patientModelList,ArrayList<EntryModel> entryModelList){
        this.context = context;
        this.patientModelList = patientModelList;
        this.entryModelList = entryModelList;
        patientModelListFull = new ArrayList<>(patientModelList);
    }

    @NonNull
    @Override
    public ArztPatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.arzt_patient_card, parent, false);
        return new ArztPatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArztPatientViewHolder holder, @SuppressLint("RecyclerView") int position) {
        int bedNr = 0;
        String bedNrS = "";

        for(int i = 0;i<entryModelList.size();i++){
            if(entryModelList.get(i).getPatientIde()==patientModelList.get(position).getPatientId()){
                bedNr = entryModelList.get(i).getBedNr();
                bedNrS = Integer.toString(bedNr);
            }
        }

        holder.text1.setText(patientModelList.get(position).getPreName()+" "+patientModelList.get(position).getName());
        holder.text2.setText(patientModelList.get(position).getBirthDate()+" (Alter)");
        holder.text4.setText("BettNr " + bedNrS);
        holder.imgbtnIcon.setImageResource(R.drawable.img);
        holder.imgbtnIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int patientIdDetails = patientModelList.get(position).getPatientId();
                Intent intent = new Intent(v.getContext(), ArztPatientDetailsActivity.class);
                intent.putExtra(EXTRA_NUMBER,patientIdDetails);
                holder.imgbtnIcon.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return patientModelList.size();
    }

    public static class ArztPatientViewHolder extends RecyclerView.ViewHolder{
        TextView text1, text2, text4;
        ImageButton imgbtnIcon;

        public ArztPatientViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.ArztPatientCardName);
            text2 = itemView.findViewById(R.id.ArztPatientCardBirthDate);
            text4 = itemView.findViewById(R.id.ArztPatientCardBettNummer);
            imgbtnIcon = itemView.findViewById(R.id.ArztPatientCardDetailsIcon);
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