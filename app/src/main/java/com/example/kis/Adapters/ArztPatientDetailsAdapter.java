package com.example.kis.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kis.R;

public class ArztPatientDetailsAdapter extends RecyclerView.Adapter<ArztPatientDetailsAdapter.ArztPatientDetailsViewHolder> {

    String[] patientArr;
    Context context;

    public ArztPatientDetailsAdapter(Context cn, String[] pArr){
        context = cn;
        patientArr = pArr;
    }

    @NonNull
    @Override
    public ArztPatientDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.arzt_patient_details_row, parent, false);
        return new ArztPatientDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArztPatientDetailsViewHolder holder, int position) {
        holder.text1.setText(patientArr[position]);
    }

    @Override
    public int getItemCount() {
        return patientArr.length;
    }

    public static class ArztPatientDetailsViewHolder extends RecyclerView.ViewHolder{
        TextView text1, text2;

        public ArztPatientDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.text1);
            text2 = itemView.findViewById(R.id.text2);
        }
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        String data1[], data2[];
        //int images[];
        Context context;

        public  MyAdapter(Context ct,String s1[], String s2[]){
            context = ct;
            data1 = s1;
            data2 = s2;
         //   images = img;
            }

            /*public void setstringsList(String ArrayOfString[]){
            this.data1 = ArrayOfString;
            notifyDataSetChanged();
            }*/

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
           View view = inflater.inflate(R.layout.arzt_patient_row, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            holder.myText1.setText(data1[position]);
            holder.myText2.setText(data2[position]);
           // holder.myImage.setImageResource(images[position]);
        }

        @Override
        public int getItemCount() {

            return data1.length;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView myText1, myText2;

            public MyViewHolder(@NonNull View itemView) {
                 super(itemView);
                 myText1 = itemView.findViewById(R.id.text1);
                 myText2 = itemView.findViewById(R.id.text2);
               // myImage = itemView.findViewById(R.id.face_image);


            }
        }
    }
}
