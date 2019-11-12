package com.friendroid.firebasemvvm.module.Registration.repository;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.friendroid.firebasemvvm.base.basemodel.Student;
import com.friendroid.firebasemvvm.module.Registration.model.RegistrationModel;
import com.friendroid.firebasemvvm.module.addstudent.view.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class RegistrationRepository {

    private FirebaseAuth mAuth;
    private Context context;
    private DatabaseReference databaseReference;

    public RegistrationRepository(Context context, DatabaseReference databaseReference) {
        this.databaseReference = databaseReference;
        this.context = context;
        mAuth = FirebaseAuth.getInstance();
    }

    // Sign up with email & password
    public void SignUpWithUserEmailAndPassword(String email, String password, final ProgressBar progressBar)
    {
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(context, "Registration Successfull", Toast.LENGTH_SHORT).show();
                    Intent a = new Intent(context, MainActivity.class);
                    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(a);
                }
                else
                {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(context, "Registration Failed", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "unexpected error for "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // send data to db when sign up with addition data
    public void addUser(String id, RegistrationModel user) {
        databaseReference.child(id).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context, "Added to database", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Added Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }




}
