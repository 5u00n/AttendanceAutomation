package com.projectopel.attendanceautomation.UI.Dashboard.Week;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projectopel.attendanceautomation.R;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class WeeklyReportFragment extends Fragment {

    RecyclerView rv;


    FirebaseDatabase database;
    DatabaseReference ref;
    FirebaseAuth auth;


    SimpleDateFormat sdfUni = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdfDay = new SimpleDateFormat("E, dd MMMM yyyy");
    Date today;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weekly_report, container, false);

        rv = view.findViewById(R.id.weekly_report_recycle_list);


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("Attendance").child(auth.getUid());

        updateUi();


        return view;
    }


    void updateUi() {

        today = Calendar.getInstance().getTime();
        // Calendar c1= Calendar.getInstance();
        //  c1.add(Calendar.DAY_OF_YEAR,-1);


        //  Log.d("Checking Yesterday :", sdfUni.format(c1.getTime())+" Day : "+c1.getTime().getDay());
        //  c1.add(c1.DAY_OF_YEAR,-1);

        //  Log.d("Checking Yesterday :", sdfUni.format(c1.getTime())+" Day : "+c1.getTime().getDay());

        /*try {
            //Log.d("Checking Yesterday :"+sdf.format(today), String.valueOf(today.compareTo(sdf.parse("19-04-2023"))));
            Log.d("Checking Yesterday :", sdf.format(c1.getTime()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }*/
        ArrayList<WeeklyReportModel> weekList = new ArrayList<>();

        ref.orderByKey().limitToLast(7).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                weekList.clear();

                ArrayList<WeeklyReportModel> weekListTemp = new ArrayList<>();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    try {
                        weekListTemp.add(new WeeklyReportModel(ds.child("status").getValue().toString(), sdfDay.format(sdfUni.parse(ds.getKey())), "--", "--"));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }


                }
                Collections.reverse(weekListTemp);
                int dayCount = 1;
                Calendar c1 = Calendar.getInstance();
                for (WeeklyReportModel wk : weekListTemp) {
                    switch (dayCount) {
                        case 1:
                            break;
                        case 2:
                        case 7:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                            c1.add(Calendar.DAY_OF_YEAR, -1);
                            if (c1.getTime().getDay() == 0) {
                                c1.add(Calendar.DAY_OF_YEAR, -1);
                            }
                            if (c1.getTime().getDay() == 6) {
                                c1.add(Calendar.DAY_OF_YEAR, -1);
                            }
                            break;
                    }

                    if (sdfDay.format(c1.getTime()).equals(wk.getDay())) {
                        weekList.add(wk);

                    } else {
                        //add value to database
                        ref.child(sdfUni.format(c1.getTime())).child("status").setValue("Absent");
                        Log.d("Data not present : ", c1.getTime().toString() + " : " + c1.getTime().getDay());
                    }


                    dayCount++;
                }

                rv.setAdapter(new WeeklyReportAdapter(weekList, getContext()));
                rv.setLayoutManager(new LinearLayoutManager(getContext()));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}