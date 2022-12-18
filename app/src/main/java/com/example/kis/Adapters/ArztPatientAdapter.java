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

import com.example.kis.Activities.ArztPatientDetailsActivity;
import com.example.kis.Models.PatientModel;
import com.example.kis.Models.EntryModel;
import com.example.kis.R;

import java.util.ArrayList;

public class ArztPatientAdapter extends RecyclerView.Adapter<ArztPatientAdapter.ArztPatientViewHolder> {

    Context context;
    ArrayList<PatientModel> patientModelList;
    ArrayList<EntryModel> entryModelList;


    public ArztPatientAdapter(Context context, ArrayList<PatientModel> patientModelList,ArrayList<EntryModel> entryModelList){
        this.context = context;
        this.patientModelList = patientModelList;
        this.entryModelList = entryModelList;
    }

    @NonNull
    @Override
    public ArztPatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.arzt_patient_row, parent, false);
        return new ArztPatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArztPatientViewHolder holder, int position) {
        int bedNr = 0;
        String bedNrS ="";
        /*
        for(int i = 0;i<entryModelList.size();i++){
            if(entryModelList.get(i).getPatientIde()==patientModelList.get(position).getPatientId()){
                bedNr = entryModelList.get(i).getBedNr();
                bedNrS = Integer.toString(bedNr);
                return;
            }
        }

         */

        holder.text1.setText(patientModelList.get(position).getPreName()+" "+patientModelList.get(position).getName());
        holder.text2.setText(patientModelList.get(position).getBirthDateS()+" (Alter)");
        holder.text3.setText("X");
        holder.text4.setText("BettNr");

        holder.icon.setImageResource(R.drawable.img);
        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ArztPatientDetailsActivity.class);
                holder.icon.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return patientModelList.size();
    }

    public static class ArztPatientViewHolder extends RecyclerView.ViewHolder{
        TextView text1, text2, text3, text4;
        ImageButton icon;

        public ArztPatientViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.ArztPatientCardName);
            text2 = itemView.findViewById(R.id.ArztPatientCardBirthDate);
            text3 = itemView.findViewById(R.id.ArztPatientCardDiagnose);
            text4 = itemView.findViewById(R.id.ArztPatientCardBettNummer);
            icon = itemView.findViewById(R.id.ArztPatientCardDetailsIcon);
        }
    }
}
