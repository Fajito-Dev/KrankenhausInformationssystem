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

import com.example.kis.Activities.StartActivity;
import com.example.kis.Database.DatabaseHelper;
import com.example.kis.Models.EntryModel;
import com.example.kis.Models.PatientModel;
import com.example.kis.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdminstrationPatientAdapter extends RecyclerView.Adapter<AdminstrationPatientAdapter.AdminstrationPatientViewHolder> {
    Context context;
    ArrayList<PatientModel> patientModelList;
    ArrayList<EntryModel> entryModelList;
    DatabaseHelper databaseHelper;

    public AdminstrationPatientAdapter(Context context, ArrayList<PatientModel> patientModelList,ArrayList<EntryModel> entryModelList,DatabaseHelper databaseHelper){
        this.context = context;
        this.patientModelList = patientModelList;
        this.entryModelList = entryModelList;
        this.databaseHelper = databaseHelper;
    }

    @NonNull
    @Override
    public AdminstrationPatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.admin_patint_card, parent, false);
        return new AdminstrationPatientAdapter.AdminstrationPatientViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
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
                EntryModel entryModel = null;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(new Date());
                try {
                    entryModel = new EntryModel(0, patientModelList.get(position).getPatientId(),date,databaseHelper.getFreeBed(),0,"k.A",false, false,"Patient eingewiesen",0,0,0,"");
                } catch (Exception e) { //dat is schwachsinn :O
                }
                databaseHelper.addEntry(entryModel);
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