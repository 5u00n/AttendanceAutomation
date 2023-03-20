package com.projectopel.attendanceautomation.UI.Dashboard.Profile;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.projectopel.attendanceautomation.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RequestsAdapter extends ArrayAdapter<RequestsModel> {

    ArrayList<RequestsModel> list = new ArrayList<>();
    Context context;
    public RequestsAdapter(@NonNull Context context, ArrayList<RequestsModel> list) {
        super(context,0, list);
        this.context=context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v= LayoutInflater.from(context).inflate(R.layout.view_home_requests,parent,false);


        TextView id,sr_no,date,reason,status;

        id=v.findViewById(R.id.view_fragment_home_request_text_id);
        sr_no=v.findViewById(R.id.view_fragment_home_request_text_sr_no);
        date=v.findViewById(R.id.view_fragment_home_request_text_date);
        reason=v.findViewById(R.id.view_fragment_home_request_text_reason);
        status=v.findViewById(R.id.view_fragment_home_request_text_status);

        RequestsModel a= getItem(position);


        id.setText(a.getId());
        sr_no.setText(a.getSr_no());
        switch (a.getStatus()){
            case "Approved":
                status.setTextColor(Color.GREEN);
                break;
            case "Rejected":
                status.setTextColor(Color.RED);
                break;
            case "Waiting":
                status.setTextColor(Color.GRAY);
                break;
        }
        status.setText(a.getStatus());
        date.setText("From : "+a.getFrom_date()+" to : "+a.getTo_date());
        reason.setText(a.getReason());

        return v;
    }
}
