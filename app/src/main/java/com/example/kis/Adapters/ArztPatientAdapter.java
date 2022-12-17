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
import com.example.kis.R;

public class ArztPatientAdapter extends RecyclerView.Adapter<ArztPatientAdapter.ArztPatientViewHolder> {

    String[] patientNames, patientBirthDates, patientDiagnoses;
    Context context;



    public ArztPatientAdapter(Context cn, String[] pArr,String[] pBD, String[] pD ){
        context = cn;
        patientNames = pArr;
        patientBirthDates = pBD;
        patientDiagnoses = pD;
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
        holder.text1.setText(patientNames[position]);
        holder.text2.setText(patientBirthDates[position]);
        holder.text3.setText(patientDiagnoses[position]);
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
        return patientNames.length;
    }

    public static class ArztPatientViewHolder extends RecyclerView.ViewHolder{
        TextView text1, text2, text3;
        ImageButton icon;

        public ArztPatientViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.ArztPatientCardName);
            text2 = itemView.findViewById(R.id.ArztPatientCardBirthDate);
            text3 = itemView.findViewById(R.id.ArztPatientCardDiagnose);
            icon = itemView.findViewById(R.id.ArztPatientCardDetailsIcon);
        }
    }
}
