package com.example.kis.Adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kis.Activities.LaborChecklistActivity;

public class LaborRequestsAdapter extends RecyclerView.Adapter<LaborRequestsAdapter.LaborRequestViewHolder> {

    public LaborRequestsAdapter(LaborChecklistActivity laborChecklistActivity) {
    }

    @NonNull
    @Override
    public LaborRequestsAdapter.LaborRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull LaborRequestsAdapter.LaborRequestViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class LaborRequestViewHolder extends RecyclerView.ViewHolder{

        public LaborRequestViewHolder(@NonNull View itemView) {

            super(itemView);
        }
    }

}
