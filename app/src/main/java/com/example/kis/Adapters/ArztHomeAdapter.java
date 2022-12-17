package com.example.kis.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kis.R;

public class ArztHomeAdapter extends RecyclerView.Adapter<ArztHomeAdapter.ArztHomeViewHolder> {

    String data1[], data2[];
    Context context;

    public ArztHomeAdapter(Context ct, String s1[], String s2[]){
        context = ct;
        data1 = s1;
        data2 = s2;
    }

    @NonNull
    @Override
    public ArztHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new ArztHomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArztHomeViewHolder holder, int position) {

        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);
    }

    @Override
    public int getItemCount() {

        return data1.length;
    }

    public class ArztHomeViewHolder extends RecyclerView.ViewHolder {

        TextView myText1, myText2;

        public ArztHomeViewHolder(@NonNull View itemView) {
             super(itemView);
             myText1 = itemView.findViewById(R.id.name_text);
             myText2 = itemView.findViewById(R.id.date_text);
        }
    }
}
