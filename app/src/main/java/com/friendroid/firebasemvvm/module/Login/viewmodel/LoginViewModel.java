package com.friendroid.firebasemvvm.module.Login.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.friendroid.firebasemvvm.module.Login.contract.LoginContract;
import com.friendroid.firebasemvvm.module.Login.repository.GoogleLoginRepository;
import com.friendroid.firebasemvvm.module.Login.repository.LoginRepository;
import com.friendroid.firebasemvvm.module.addstudent.view.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginViewModel extends AndroidViewModel implements LoginContract.auth, LoginContract.googleSignIn {

    private LoginRepository repository;
    private GoogleLoginRepository googleLoginRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = new LoginRepository(application);
        googleLoginRepository = new GoogleLoginRepository(application);

    }

    @Override
    public void loginCredential(String email, String password, ProgressBar progressBar) {
        repository.LoginWithUserEmailAndPassword(email, password, progressBar);
    }

    @Override
    public void firebaseAuthWithGoogle(GoogleSignInAccount account, ProgressBar progressBar) {
        googleLoginRepository.firebaseAuthWithGoogle(account, progressBar);
    }

    @Override
    public void updateUI(Activity activity, FirebaseAuth mAuth) {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            activity.startActivity(new Intent(activity, MainActivity.class));
        }
    }


}
