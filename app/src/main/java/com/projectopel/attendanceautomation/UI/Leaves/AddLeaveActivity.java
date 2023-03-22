package com.projectopel.attendanceautomation.UI.Leaves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.projectopel.attendanceautomation.R;
import com.projectopel.attendanceautomation.UI.Dashboard.Profile.RequestsModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddLeaveActivity extends AppCompatActivity {

    Button submit, select_document;
    ImageButton back;

    EditText reason, reason_details;
    TextView filename, file_url, from_date, to_date;
    ImageView file_imageview;


    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference data_ref;

    FirebaseStorage storage;
    StorageReference stor_ref;

    private static int PICK_IMAGE = 123;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
    Uri imageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_leave);


        auth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        data_ref = database.getReference();

        storage = FirebaseStorage.getInstance();
        stor_ref = storage.getReference();

        initUI();
    }

    private void initUI() {

        reason = findViewById(R.id.add_leave_reason);
        from_date = findViewById(R.id.add_leave_from_date);
        to_date = findViewById(R.id.add_leave_to_date);
        reason_details = findViewById(R.id.add_leave_reason_details);

        file_imageview = findViewById(R.id.add_leave_image_file);

        filename = findViewById(R.id.add_leave_filename);
        file_url = findViewById(R.id.add_leave_file_url);

        back = findViewById(R.id.add_leave_imagebutton_goback);

        submit = findViewById(R.id.add_leave_button_submit);
        select_document = findViewById(R.id.add_leave_select_document);

        back.setOnClickListener(v -> {
            startActivity(new Intent(AddLeaveActivity.this, LeaveActivity.class));
            finish();
        });

        submit.setOnClickListener(v -> checkAllFields());

        select_document.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(intent, PICK_IMAGE);
        });

        from_date.setOnClickListener(v -> getDate(1));
        to_date.setOnClickListener(v -> getDate(2));


        updateUI();

    }


    private void updateUI() {
    }

    private void getDate(int from) {
        LayoutInflater li = LayoutInflater.from(AddLeaveActivity.this);
        View promptsView = li.inflate(R.layout.prompt_select_date, null);

        final DatePicker datePicker = promptsView.findViewById(R.id.prompt_add_leave_date);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddLeaveActivity.this);

        // set prompts.xml to alertdialog builder
        alertDialogBuilder.setView(promptsView);

        //final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);


        // set dialog message
        alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                String dt =datePicker.getDayOfMonth() + "/" + (datePicker.getMonth()+1)+ "/" + datePicker.getYear();

                //datePicker.get

                String date = null;
                try {
                    date = sdf.format(sdf.parse(dt));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                String todayDate= sdf.format(Calendar.getInstance().getTime());

                    if (from == 1) {
                        if (!to_date.getText().toString().isEmpty()) {

                            try {
                                if (sdf.parse(to_date.getText().toString()).before(sdf.parse(date))) {

                                    from_date.setText("");
                                    from_date.setHint("Set before To Date!");
                                    from_date.setHintTextColor(Color.RED);
                                } else if (!sdf.parse(date).after(sdf.parse(sdf.format(Calendar.getInstance().getTime())))){
                                    from_date.setText("");
                                    from_date.setHint("Set after Today's Date!");
                                    from_date.setHintTextColor(Color.RED);
                                } else {

                                    from_date.setText(date);
                                }
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        } else {

                            try {

                                if (!sdf.parse(date).after(sdf.parse(sdf.format(Calendar.getInstance().getTime())))){
                                    from_date.setText("");
                                    from_date.setHint("Set after Today's Date!");
                                    from_date.setHintTextColor(Color.RED);
                                } else {
                                    from_date.setText(date);
                                }
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    if (from == 2) {
                        if (!from_date.getText().toString().isEmpty()) {
                            Log.d("Date to +++++", date);
                            try {
                                if (sdf.parse(date).before(sdf.parse(from_date.getText().toString()))) {
                                    to_date.setText("");
                                    to_date.setHint("Set after From Date!");
                                    to_date.setHintTextColor(Color.RED);
                                }else{
                                    to_date.setText(date);
                                }
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            to_date.setText(date);
                        }

                    }

                //  if ((name.getText().toString().isEmpty() || disc.getText().toString().isEmpty()) || (life_span.getText().toString().isEmpty() || humidity.getText().toString().isEmpty()) || (fertilizer.getText().toString().isEmpty() || water_time.getText().toString().isEmpty()) || (light.getText().toString().isEmpty() || temp.getText().toString().isEmpty())) {
                //   } else {
                // do something with the input

                //     Log.d("Dialog ", "__" + name.getText().length() + "--");
                //   sendImagetoStorage(new PlantModel(String.valueOf((System.currentTimeMillis() / 1000)), name.getText().toString(), disc.getText().toString(), life_span.getText().toString(), humidity.getText().toString(), fertilizer.getText().toString(), water_time.getText().toString(), light.getText().toString(), temp.getText().toString(), ""), imageUri);
                //  }
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
    }
    
    private void checkAllFields(){
        if(reason.getText().toString().isEmpty() || from_date.getText().toString().isEmpty()||to_date.getText().toString().isEmpty()||reason_details.getText().toString().isEmpty()){
            Toast.makeText(this, "Please give all Information", Toast.LENGTH_SHORT).show();
        }else {
            finalConfirmation();
        }
    }
    
    
    private void finalConfirmation(){
        AlertDialog alertDialog = new AlertDialog.Builder(AddLeaveActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Are you sure you want to submit ?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(AddLeaveActivity.this, "OK", Toast.LENGTH_SHORT).show();
                        submitData();
                    }
                });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

    private void submitData() {

        RequestsModel rm = new RequestsModel();

        Date date= new Date();
        rm.setId(auth.getUid()+"_"+Calendar.getInstance().getTime().toString());
        rm.setStatus("Waiting");
        rm.setDate_generated(sdf.format(date));
        rm.setReason(reason.getText().toString());
        rm.setFrom_date(from_date.getText().toString());
        rm.setTo_date(to_date.getText().toString());
        rm.setReason_details(reason_details.getText().toString());

        if(imageUri !=null) {
            sendImagetoStorage(rm, imageUri);
        }
        else {
            sendToRealtimeDatabase(rm);
        }

       
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // In fragment class callback

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();

            file_imageview.setImageURI(imageUri);
            file_imageview.setVisibility(View.VISIBLE);
            Log.d("Img Uri ------", data.getData().toString());
        }


    }


    private void sendImagetoStorage(RequestsModel rs, Uri imguri) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        //StorageReference imageref = storageRef.child("Images").child(firebaseAuth.getUid()).child("Profile Pic");

        StorageReference imageref = storageRef.child("users").child(auth.getUid()).child("leave").child(rs.getId());

        UploadTask uploadTask = imageref.putFile(imguri);
        final Uri[] downloadUri = new Uri[1];
        uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                // Continue with the task to get the download URL
                return imageref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    downloadUri[0] = task.getResult();
                    Log.d("Download uri", downloadUri[0].toString());
                   // file_url.setImgurl(downloadUri[0].toString());
                    sendToRealtimeDatabase(rs);

                } else {
                    // Handle failures
                    // ...
                }
            }
        });


    }

    public void sendToRealtimeDatabase(RequestsModel rs) {

        data_ref.child("users").child(auth.getUid()).child("requests").child(rs.getId()).setValue(rs);
        data_ref.child("requests").child(rs.getId()).setValue(rs);

        imageUri = null;
        finish();

    }

}