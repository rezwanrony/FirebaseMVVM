package com.friendroid.firebasemvvm.module.Login.repository;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.friendroid.firebasemvvm.module.addstudent.view.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginRepository {

    private FirebaseAuth mAuth;
    private Context context;

    public LoginRepository(Context context) {
        this.context = context;
        mAuth = FirebaseAuth.getInstance();
    }

    public void LoginWithUserEmailAndPassword(String email, String password,final ProgressBar progressBar)
    {
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(context, "Login Successfull", Toast.LENGTH_SHORT).show();
                    Intent a = new Intent(context, MainActivity.class);
                    a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(a);
                }
                else
                {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show();
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

}
