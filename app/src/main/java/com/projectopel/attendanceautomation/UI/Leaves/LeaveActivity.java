package com.projectopel.attendanceautomation.UI.Leaves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.projectopel.attendanceautomation.R;
import com.projectopel.attendanceautomation.UI.Dashboard.DashboardActivity;
import com.projectopel.attendanceautomation.UI.Dashboard.Profile.RequestsModel;

import java.util.ArrayList;
import java.util.Collections;

public class LeaveActivity extends AppCompatActivity {


    ImageButton backButton;
    FloatingActionButton add_leave;
    RecyclerView recyclerView;


    //Firebase
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference data_ref;

    FirebaseStorage storage;
    StorageReference stor_ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);

        auth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        data_ref= database.getReference();
        initUI();

        updateUI();
    }

    private void initUI() {

        backButton= findViewById(R.id.leave_imagebutton_goback);
        add_leave= findViewById(R.id.leave_floating_add_leave);
        recyclerView= findViewById(R.id.leave_list_reports);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LeaveActivity.this, DashboardActivity.class));finish();
            }
        });


        add_leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(LeaveActivity.this, AddLeaveActivity.class));
            }
        });

    }

    private void updateUI() {

        ArrayList<RequestsModel> ll = new ArrayList<>();
        data_ref.child("users").child(auth.getUid()).child("requests").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                int i = 1;

                if (snapshot.exists()){
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        ll.add(new RequestsModel(ds.getKey(), String.valueOf(i), ds.child("status").getValue().toString(), ds.child("from_date").getValue().toString(), ds.child("to_date").getValue().toString(), ds.child("reason").getValue().toString()));
                        i++;

                    }

                    Collections.reverse(ll);

                    recyclerView.setAdapter(new LeaveReportAdapter(LeaveActivity.this, ll));
                    recyclerView.setLayoutManager(new LinearLayoutManager(LeaveActivity.this));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}