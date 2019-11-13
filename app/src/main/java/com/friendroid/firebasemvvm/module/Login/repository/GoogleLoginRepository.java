package com.friendroid.firebasemvvm.module.Login.repository;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.friendroid.firebasemvvm.module.addstudent.view.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class GoogleLoginRepository {

    private Context context;
    private FirebaseAuth mAuth;

    public GoogleLoginRepository(Context context) {
        this.context = context;
        mAuth = FirebaseAuth.getInstance();

    }

    public void firebaseAuthWithGoogle(GoogleSignInAccount account, final ProgressBar progressBar) {

        AuthCredential credential = GoogleAuthProvider
                .getCredential(account.getIdToken(),null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @SuppressLint("ShowToast")
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    progressBar.setVisibility(View.GONE);
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    gotoMainFunction();

                }
                else
                {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(context, "Somethings Went Wrong", Toast.LENGTH_SHORT);
                    //updateUI(null);
                }
            }
        });

    }


    private void gotoMainFunction() {

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
        if (account != null) {
            String personName = account.getDisplayName();
            String personGivenName = account.getGivenName();
            String personFamilyName = account.getFamilyName();
            String personEmail = account.getEmail();
            String personId = account.getId();
            Uri personPhoto = account.getPhotoUrl();
            Toast.makeText(context, "Welcome " + personEmail, Toast.LENGTH_SHORT);
            //userPreferences.setStringValue("email",personEmail);
            context.startActivity(new Intent(context, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

    }

}
