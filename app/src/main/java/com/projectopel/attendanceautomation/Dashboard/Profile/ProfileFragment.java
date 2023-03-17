package com.projectopel.attendanceautomation.Dashboard.Profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.projectopel.attendanceautomation.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    ImageView profile;
    TextView name, address, info_text, absent_stats, present_stats, leave_stats,total_stats;
    Button report, manual_attendance;
    ListView request_list;

    DataSnapshot snapshot = null;

    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth auth;

    public ProfileFragment(DataSnapshot snapshot) {
        this.snapshot = snapshot;
    }

    public ProfileFragment() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        name = v.findViewById(R.id.profile_username);
        address = v.findViewById(R.id.profile_address);
        info_text=v.findViewById(R.id.profile_info_text);
        absent_stats= v.findViewById(R.id.profile_absent_stats);
        present_stats= v.findViewById(R.id.profile_present_stats);
        leave_stats=v.findViewById(R.id.profile_leave_stats);
        total_stats= v.findViewById(R.id.profile_total_stats);

        profile= v.findViewById(R.id.fragment_profile_image);

        report=v.findViewById(R.id.profile_button_report);
        manual_attendance= v.findViewById(R.id.profile_button_manual_attendance);




        request_list = v.findViewById(R.id.profile_list_reports);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        reference = database.getReference("users").child(auth.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Log.d("Snapshot ++++", snapshot.getKey());

                if(snapshot.exists()) {

                    if(snapshot.child("profile_image_url").exists()){
                        Picasso.get().load(snapshot.child("profile_image_url").getValue().toString()).into(profile);
                    }
                    name.setText(snapshot.child("name").getValue().toString());
                    address.setText(snapshot.child("address").child("place").getValue().toString() + ", " + snapshot.child("address").child("city").getValue().toString() + ", " + snapshot.child("address").child("pin").getValue().toString());


                    String absent,present,leave;
                    absent=snapshot.child("attendance_stats").child("absent_stats").getValue().toString();
                    present=snapshot.child("attendance_stats").child("present_stats").getValue().toString();
                    leave=snapshot.child("attendance_stats").child("leave_stats").getValue().toString();


                    absent_stats.setText(absent);
                    present_stats.setText(present);
                    leave_stats.setText(leave);
                    total_stats.setText(String.valueOf(Integer.parseInt(absent)+Integer.parseInt(present)+Integer.parseInt(leave)));



                    ArrayList<RequestsModel> ll = new ArrayList<>();
                    ll.add(new RequestsModel("jkaghfduiass", "1", "Waiting", "18/03/2023", "26/02/2023", "Personal'k,jmp;lol nn Issue"));
                    ll.add(new RequestsModel("wjkaghfduias", "2", "Rejected", "20/02/2023", "26/02/2023", "Sick Leave"));

                    request_list.setAdapter(new RequestsAdapter(getContext(), ll));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return v;
    }
}