package com.projectopel.attendanceautomation.UI.Leaves;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.projectopel.attendanceautomation.R;

public class AddLeaveActivity extends AppCompatActivity {

    Button submit,select_document;
    ImageButton back;

    EditText reason,from_date,to_date,reason_details;
    TextView filename, file_url;
    ImageView file_imageview;


    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference data_ref;

    FirebaseStorage storage;
    StorageReference stor_ref;

    private static int PICK_IMAGE = 123;
    Uri imageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_leave);


        auth= FirebaseAuth.getInstance();

        database= FirebaseDatabase.getInstance();
        data_ref= database.getReference();

        storage= FirebaseStorage.getInstance();
        stor_ref= storage.getReference();

        initUI();
    }

    private void initUI() {

        reason= findViewById(R.id.add_leave_reason);
        from_date= findViewById(R.id.add_leave_from_date);
        to_date= findViewById(R.id.add_leave_to_date);
        reason_details=findViewById(R.id.add_leave_reason_details);

        file_imageview= findViewById(R.id.add_leave_image_file);

        filename= findViewById(R.id.add_leave_filename);
        file_url= findViewById(R.id.add_leave_file_url);

        back= findViewById(R.id.add_leave_imagebutton_goback);

        submit= findViewById(R.id.add_leave_button_submit);
        select_document= findViewById(R.id.add_leave_select_document);

        back.setOnClickListener(v -> {
            startActivity(new Intent(AddLeaveActivity.this,LeaveActivity.class));
            finish();
        });

        submit.setOnClickListener(v -> submitData());

        select_document.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE);
        });

        updateUI();

    }


    private void updateUI() {
    }

    private void submitData() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // In fragment class callback

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            Log.d("Img Uri ------", data.getData().toString());
        }


    }

}