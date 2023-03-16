package com.projectopel.attendanceautomation.Dashboard.Week;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.projectopel.attendanceautomation.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeeklyReportAdapter extends  RecyclerView.Adapter<WeeklyReportAdapter.viewHolder>{

ArrayList<WeeklyReportModel> list;
Context context;

    public WeeklyReportAdapter(ArrayList<WeeklyReportModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_weekly_report,parent,false);


        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        WeeklyReportModel model = list.get(position);
        holder.imageView.setImageResource(R.drawable.rectanglegreen);
        holder.day.setText(model.getDay());
        holder.intime.setText(model.getIntime());
        holder.outtime.setText(model.outtime);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView day, intime,outtime;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.presentybox);
            day = itemView.findViewById(R.id.weekly_report_day);
            intime = itemView.findViewById(R.id.weekly_report_intime);
            outtime =intime.findViewById(R.id.weekly_report_outtime);
        }
    }


}

