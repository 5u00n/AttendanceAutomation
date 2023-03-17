package com.projectopel.attendanceautomation.Dashboard;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projectopel.attendanceautomation.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    //Firebase
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;


    private static final int MY_LOCATION_REQUEST_CODE = 2;
    TabLayout tabLayout;
    ViewPager viewPager;

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;


    private static final int NOTIFICATION_ID = 1;
    private static final String CHANNEL_ID = "MyNotificationChannel";
    private static final String CHANNEL_NAME = "My Notification Channel";
    private static final String CHANNEL_DESC = "Channel for my daily notifications";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);
        toolbar = findViewById(R.id.dashboard_toolbar);

        setSupportActionBar(toolbar);

        Log.d("Activity Check","-----  In dashboard Activity");


        drawerLayout = findViewById(R.id.dashboard_drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.dashboard_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager = findViewById(R.id.home_viewpager);
        tabLayout = findViewById(R.id.home_tabs);

        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.addTab(tabLayout.newTab().setText("Weekly"));
        tabLayout.addTab(tabLayout.newTab().setText("Monthly"));


        getPermission();
        updateUI();

    }

    void updateUI(){

        auth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        databaseReference= database.getReference();



        // tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);



                    viewPager.setAdapter(new DashboardAdapter(DashboardActivity.this, getSupportFragmentManager(), tabLayout.getTabCount()));
                    viewPager.getCurrentItem();


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    public void getPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_LOCATION_REQUEST_CODE);

            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Location permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.menu_leave:
                Toast.makeText(this, "Leave", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_reports:
                Toast.makeText(this, "Reports", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_about_app:
                Toast.makeText(this, "About App", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_complain_error:
                Toast.makeText(this, "Complain Error", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_help:
                Toast.makeText(this, "Help !", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onResume() {
        updateUI();
        super.onResume();
    }

    @Override
    protected void onStart() {

        super.onStart();
        updateUI();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        updateUI();
    }
}