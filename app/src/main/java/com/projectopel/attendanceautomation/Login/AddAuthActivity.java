package com.projectopel.attendanceautomation.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.projectopel.attendanceautomation.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static android.content.ContentValues.TAG;

public class AddAuthActivity extends AppCompatActivity {

    EditText newPassword,confirmPassword;
    Button submit;

    Intent intent;
    String email,password;


    FirebaseDatabase database;
    DatabaseReference reference;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_auth);


        auth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        reference=database.getReference();

        intent= getIntent();
        email= intent.getStringExtra("emailID");


        submit= findViewById(R.id.addauth_button_submit);


        newPassword= findViewById(R.id.addauth_new_password);
        confirmPassword= findViewById(R.id.addauth_confirm_password);




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(newPassword.getText().toString().equals(confirmPassword.getText().toString())) {
                    validateAndUpdate(email, confirmPassword.getText().toString());
                }else {
                    Toast.makeText(AddAuthActivity.this, "Password Doesn't Match !", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void validateAndUpdate(String email,String password) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = auth.getCurrentUser();
                    goToAddFaceData(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(AddAuthActivity.this, "Authentication failed. Try Again !",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void goToAddFaceData(FirebaseUser user) {
        startActivity(new Intent(AddAuthActivity.this,AddFaceDataActivity.class));
        finish();

    }
}