package com.projectopel.attendanceautomation;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projectopel.attendanceautomation.Dashboard.DashboardActivity;
import com.projectopel.attendanceautomation.Login.AddFaceDataActivity;
import com.projectopel.attendanceautomation.Login.LoginActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    FirebaseAuth auth;
    FirebaseUser cuser;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        cuser = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        reference = database.getReference();
        checkAuth();

        //startActivity(new Intent(this, DashboardActivity.class));
        //finish();
    }

    private void checkAuth() {

        if (cuser == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            reference.child("users").child(auth.getUid()).child("f-data").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        startActivity(new Intent(MainActivity.this, DashboardActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(MainActivity.this, AddFaceDataActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}