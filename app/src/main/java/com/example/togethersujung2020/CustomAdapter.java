package com.example.togethersujung2020;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.togethersujung2020.ui.main.MainActivity;

import java.security.AccessControlContext;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private ArrayList<User> arrayList;
    private Context mContext;

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
            public View mView;
            TextView title;
            TextView date;
            TextView content;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            this.title = itemView.findViewById(R.id.title);
            this.date = itemView.findViewById(R.id.date);
            this.content = itemView.findViewById(R.id.content);
        }

        public void onClick(View v) {
            System.out.println(getAdapterPosition()); //getposition()이 줄쳐져 있던데 뭐지?
            Intent intent = new Intent(v.getContext(), NewActivity.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            v.getContext().startActivity(intent);
        }
    }


    public CustomAdapter(ArrayList<User> arrayList, MainActivity mainActivity) {
        Log.e("[IMPORTANT] ", "init size" + arrayList.size());
        this.arrayList = arrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, final int position) {
        holder.title.setText(arrayList.get(position).getTitle());
        holder.date.setText(arrayList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }
}