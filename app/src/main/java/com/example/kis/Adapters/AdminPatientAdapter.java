package com.example.kis.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kis.Database.DatabaseHelper;
import com.example.kis.Models.EntryModel;
import com.example.kis.Models.PatientModel;
import com.example.kis.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdminPatientAdapter extends RecyclerView.Adapter<AdminPatientAdapter.AdminstrationPatientViewHolder> implements Filterable {
    Context context;
    ArrayList<PatientModel> patientModelList;
    ArrayList<EntryModel> entryModelList;
    DatabaseHelper databaseHelper;
    ArrayList<PatientModel> patientModelListFull;

    public AdminPatientAdapter(Context context, ArrayList<PatientModel> patientModelList, ArrayList<EntryModel> entryModelList, DatabaseHelper databaseHelper){
        this.context = context;
        this.patientModelList = patientModelList;
        this.entryModelList = entryModelList;
        this.databaseHelper = databaseHelper;
        patientModelListFull = new ArrayList<>(patientModelList);
    }

    @NonNull
    @Override
    public AdminstrationPatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.admin_patint_card, parent, false);
        return new AdminPatientAdapter.AdminstrationPatientViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdminPatientAdapter.AdminstrationPatientViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String insuranceNr = Integer.toString(patientModelList.get(position).getPatientId());
        String bedNrS = "";
        int bedNRnull = 0;
        for(int i = 0;i<entryModelList.size();i++){
            if(entryModelList.get(i).getPatientIde()==patientModelList.get(position).getPatientId()){
                bedNrS = Integer.toString(entryModelList.get(i).getBedNr());
                bedNRnull = entryModelList.get(i).getBedNr();
            }
        }

        holder.tvName.setText(patientModelList.get(position).getPreName()+" "+patientModelList.get(position).getName());
        holder.tvBirthday.setText(patientModelList.get(position).getBirthDate()+" " + "("+patientModelList.get(position).getAge()+")");

        if(bedNRnull == 0){
            holder.tvBedNr.setText("Patient ausgewiesen");
            holder.imgbtnIcon.setImageResource(R.drawable.img2);
        }else {
            holder.tvBedNr.setText("Bett " + bedNrS);
            holder.imgbtnIcon.setImageResource(R.drawable.img3);
        }
        holder.tvInsuranceNr.setText("VNr "+insuranceNr);

        int finalBedNRnull = bedNRnull;

        holder.imgbtnIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EntryModel entryModel = null;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(new Date());

                if(finalBedNRnull == 0) {
                    try {
                        entryModel = new EntryModel(0, patientModelList.get(position).getPatientId(), date, databaseHelper.getFreeBed(), 0, "k.A", false, false, "Patient eingewiesen", 0, 0, 0, "");
                    } catch (Exception e) { //dat is schwachsinn :O
                    }

                    databaseHelper.addEntry(entryModel);
                    holder.imgbtnIcon.setImageResource(R.drawable.img);
                    entryModelList.add(entryModel);
                    notifyDataSetChanged();
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return patientModelList.size();
    }
    public static class AdminstrationPatientViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvBirthday, tvBedNr, tvInsuranceNr;
        ImageButton imgbtnIcon;

        public AdminstrationPatientViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.AdministrationPatientCardName);
            tvBirthday = itemView.findViewById(R.id.AdministrationPatientCardBirthDate);
            tvBedNr = itemView.findViewById(R.id.AdministrationPatientCardBedNr);
            tvInsuranceNr = itemView.findViewById(R.id.AdministrationPatientCardInsuranceNumber);
            imgbtnIcon = itemView.findViewById(R.id.AdministrationPatientCardImageButtonDetails);
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