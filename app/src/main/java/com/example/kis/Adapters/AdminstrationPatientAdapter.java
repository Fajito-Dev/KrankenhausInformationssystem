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

import com.example.kis.Activities.ArztPatientDetailsActivity;
import com.example.kis.Activities.StartActivity;
import com.example.kis.Models.EntryModel;
import com.example.kis.Models.PatientModel;
import com.example.kis.R;

import java.util.ArrayList;

public class AdminstrationPatientAdapter extends RecyclerView.Adapter<AdminstrationPatientAdapter.AdminstrationPatientViewHolder> {
    Context context;
    ArrayList<PatientModel> patientModelList;
    ArrayList<EntryModel> entryModelList;


    public AdminstrationPatientAdapter(Context context, ArrayList<PatientModel> patientModelList,ArrayList<EntryModel> entryModelList){
        this.context = context;
        this.patientModelList = patientModelList;
        this.entryModelList = entryModelList;
    }

    @NonNull
    @Override
    public AdminstrationPatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.admin_patint_card, parent, false);
        return new AdminstrationPatientAdapter.AdminstrationPatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminstrationPatientAdapter.AdminstrationPatientViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String insuranceNr = Integer.toString(patientModelList.get(position).getPatientId());
        String bedNrS = "";
        for(int i = 0;i<entryModelList.size();i++){
            if(entryModelList.get(i).getPatientIde()==patientModelList.get(position).getPatientId()){
                bedNrS = Integer.toString(entryModelList.get(i).getBedNr());
            }
        }
        holder.tvName.setText(patientModelList.get(position).getPreName()+" "+patientModelList.get(position).getName());
        holder.tvBirthday.setText(patientModelList.get(position).getBirthDate()+" (Alter)");
        holder.tvBedNr.setText("Bett " + bedNrS);
        holder.tvInsuranceNr.setText(insuranceNr);

        holder.imgbtnIcon.setImageResource(R.drawable.img);
        holder.imgbtnIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), StartActivity.class);
                holder.imgbtnIcon.getContext().startActivity(intent);
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
}