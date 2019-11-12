package com.friendroid.firebasemvvm.module.Login.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.friendroid.firebasemvvm.R;
import com.friendroid.firebasemvvm.databinding.ActivityLoginBinding;
import com.friendroid.firebasemvvm.module.Login.contract.LoginContract;
import com.friendroid.firebasemvvm.module.Login.viewmodel.LoginViewModel;
import com.friendroid.firebasemvvm.module.Registration.view.RegistrationActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.SignUp {

    private ActivityLoginBinding loginBinding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginBinding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginBinding.email.getText().toString();
                String password = loginBinding.password.getText().toString();
                viewModel.loginCredential(email,password, loginBinding.progressCircular);
            }
        });

        loginBinding.signuptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    gotosignup(v);
            }
        });

    }

    @Override
    public void gotosignup(View view) {
        startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
    }
}
