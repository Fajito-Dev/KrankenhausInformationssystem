package com.example.krankenhaus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

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
       View view = inflater.inflate(R.layout.my_row, parent, false);
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
        ImageView myImage;

        public MyViewHolder(@NonNull View itemView) {
             super(itemView);
             myText1 = itemView.findViewById(R.id.name_text);
             myText2 = itemView.findViewById(R.id.date_text);
           // myImage = itemView.findViewById(R.id.face_image);


        }
    }
}
