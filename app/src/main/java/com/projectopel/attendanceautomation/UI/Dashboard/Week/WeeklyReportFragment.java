package com.projectopel.attendanceautomation.UI.Dashboard.Week;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.projectopel.attendanceautomation.R;
import com.projectopel.attendanceautomation.UI.Leaves.LeaveActivity;
import com.projectopel.attendanceautomation.UI.Leaves.LeaveReportAdapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WeeklyReportFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_weekly_report, container, false);

        RecyclerView rv = view.findViewById(R.id.weekly_report_recycle_list);

        ArrayList<WeeklyReportModel> weekList= new ArrayList<>();

        weekList.add(new WeeklyReportModel("present","Monday,10th April 2023","11:20","4:30"));
        weekList.add(new WeeklyReportModel("absent", "Tuesday, 10th April 2023","--:--","--:--"));
        rv.setAdapter(new WeeklyReportAdapter( weekList,getContext()));
        rv.setLayoutManager(new LinearLayoutManager(getContext()));




        return  view;
    }
}