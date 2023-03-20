package com.projectopel.attendanceautomation;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projectopel.attendanceautomation.UI.Dashboard.DashboardActivity;
import com.projectopel.attendanceautomation.Login.AddFaceDataActivity;
import com.projectopel.attendanceautomation.Login.LoginActivity;
import com.projectopel.attendanceautomation.UI.Leaves.AddLeaveActivity;
import com.projectopel.attendanceautomation.UI.Leaves.LeaveActivity;

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

        setContentView(R.layout.activity_main);

        Log.d("Activity Check","-----  In Main Activity");
        auth = FirebaseAuth.getInstance();

        cuser = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        reference = database.getReference();
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean connected = (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED);
        if(connected){
            checkAuth();
        }else{

            Snackbar snackbar = Snackbar.make(findViewById(R.id.main_layout),"No Internet Connection",Snackbar.LENGTH_LONG).setAction("Retry", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Refresh Page Code Page
                }
            });
            snackbar.show();
        }


        //startActivity(new Intent(this, DashboardActivity.class));
        //finish();
    }

    private void checkAuth() {



        if (cuser == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {


            reference.child("users").child(auth.getUid()).child("f-data").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Log.d("------------ Logic","snapshots");
                    if (snapshot.exists()) {


                        startActivity(new Intent(MainActivity.this, AddLeaveActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(MainActivity.this, AddFaceDataActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.d("+++++ Error Check",error.toString());

                }


            });
        }
    }
}