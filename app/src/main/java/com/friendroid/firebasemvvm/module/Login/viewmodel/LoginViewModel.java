package com.friendroid.firebasemvvm.module.Login.viewmodel;

import android.app.Application;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.friendroid.firebasemvvm.module.Login.contract.LoginContract;
import com.friendroid.firebasemvvm.module.Login.repository.LoginRepository;

public class LoginViewModel extends AndroidViewModel implements LoginContract.auth {

    private LoginRepository repository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        repository = new LoginRepository(application);
    }

    @Override
    public void loginCredential(String email, String password, ProgressBar progressBar) {
        repository.LoginWithUserEmailAndPassword(email,password,progressBar);
    }
}
