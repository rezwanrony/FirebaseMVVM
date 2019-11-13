package com.friendroid.firebasemvvm.module.Login.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.facebook.AccessToken;
import com.friendroid.firebasemvvm.module.Login.contract.LoginContract;
import com.friendroid.firebasemvvm.module.Login.repository.FacebookLoginRepository;
import com.friendroid.firebasemvvm.module.Login.repository.LoginRepository;
import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends AndroidViewModel implements LoginContract.auth ,LoginContract.FacebookLogin{

    private LoginRepository repository;
    private FacebookLoginRepository facebookrepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = new LoginRepository(application);
        facebookrepository=new FacebookLoginRepository(application);
    }

    @Override
    public void loginCredential(String email, String password, ProgressBar progressBar) {
        repository.LoginWithUserEmailAndPassword(email,password,progressBar);
    }

    @Override
    public void signinwithfacebook(Activity activity,AccessToken accessToken) {
        facebookrepository.handleFacebookAccessToken(activity,accessToken);
    }
}
