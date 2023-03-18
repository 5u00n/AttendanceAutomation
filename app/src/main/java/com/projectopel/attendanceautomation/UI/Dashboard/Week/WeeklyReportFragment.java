package com.projectopel.attendanceautomation.UI.Dashboard.Week;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.projectopel.attendanceautomation.R;

import androidx.fragment.app.Fragment;

public class WeeklyReportFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weekly_report, container, false);
    }
}