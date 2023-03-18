package com.projectopel.attendanceautomation.UI.Leaves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

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

public class LeaveActivity extends AppCompatActivity {


    ImageButton backButton;
    FloatingActionButton add_leave;
    ListView listView;


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

    private void updateUI() {

        data_ref.child("users").child(auth.getUid()).child("request").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initUI() {

        backButton= findViewById(R.id.leave_imagebutton_goback);
        add_leave= findViewById(R.id.leave_floating_add_leave);
        listView= findViewById(R.id.leave_list_reports);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}