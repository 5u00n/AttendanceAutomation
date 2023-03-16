package com.projectopel.attendanceautomation.Dashboard.Month;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.projectopel.attendanceautomation.R;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class MonthlyReportFragment extends Fragment {

    public MonthlyReportFragment(){

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_monthly_report, container, false);

        RecyclerView recyclerView = v.findViewById(R.id.monthly_report_recycle_list);

        return v;
    }
}