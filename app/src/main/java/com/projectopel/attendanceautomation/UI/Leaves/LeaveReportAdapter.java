package com.projectopel.attendanceautomation.UI.Leaves;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.projectopel.attendanceautomation.R;
import com.projectopel.attendanceautomation.UI.Dashboard.Profile.RequestsModel;
import com.projectopel.attendanceautomation.UI.Dashboard.Week.WeeklyReportModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LeaveReportAdapter extends  RecyclerView.Adapter<com.projectopel.attendanceautomation.UI.Leaves.LeaveReportAdapter.viewHolder>{

    ArrayList<RequestsModel> list;
    Context context;

    public LeaveReportAdapter(Context context,ArrayList<RequestsModel> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public com.projectopel.attendanceautomation.UI.Leaves.LeaveReportAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_home_requests,parent,false);
        return new com.projectopel.attendanceautomation.UI.Leaves.LeaveReportAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull com.projectopel.attendanceautomation.UI.Leaves.LeaveReportAdapter.viewHolder holder, int position) {
        RequestsModel model = list.get(position);
        holder.id.setText(model.getId());
        holder.status.setText(model.getStatus());
        holder.sr_no.setText(model.getSr_no());
        switch (model.getStatus()){
            case "Approved":
                holder.status.setTextColor(Color.GREEN);
                break;
            case "Rejected":
                holder.status.setTextColor(Color.RED);
                break;
            case "Waiting":
                holder.status.setTextColor(Color.GRAY);
                break;
        }

        holder.date.setText("From : "+model.getFrom_date()+" To : "+model.getTo_date());
        holder.reason.setText(model.getReason());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView id,sr_no,date,reason,status;

        public viewHolder(@NonNull View v) {
            super(v);
            id=v.findViewById(R.id.view_fragment_home_request_text_id);
            sr_no=v.findViewById(R.id.view_fragment_home_request_text_sr_no);
            date=v.findViewById(R.id.view_fragment_home_request_text_date);
            reason=v.findViewById(R.id.view_fragment_home_request_text_reason);
            status=v.findViewById(R.id.view_fragment_home_request_text_status);
        }
    }


}

