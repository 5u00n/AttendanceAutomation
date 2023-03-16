package com.projectopel.attendanceautomation.Dashboard;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.projectopel.attendanceautomation.Dashboard.Profile.ProfileFragment;
import com.projectopel.attendanceautomation.Dashboard.Month.MonthlyReportFragment;
import com.projectopel.attendanceautomation.Dashboard.Week.WeeklyReportFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class DashboardAdapter extends FragmentPagerAdapter {

    int totalTabs;

    Context context;

    DataSnapshot snapshot;
    public DashboardAdapter(Context context, FragmentManager fm, int totalTabs,DataSnapshot snapshot) {
        super(fm);
        this.context= context;
        this.totalTabs = totalTabs;
        this.snapshot= snapshot;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new ProfileFragment(snapshot);
            case 1:
                return new WeeklyReportFragment();
            case 2:
                return new MonthlyReportFragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
