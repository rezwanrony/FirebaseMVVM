package com.friendroid.firebasemvvm.module.Login.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.friendroid.firebasemvvm.R;
import com.friendroid.firebasemvvm.databinding.ActivityLoginBinding;
import com.friendroid.firebasemvvm.module.Login.contract.LoginContract;
import com.friendroid.firebasemvvm.module.Login.viewmodel.LoginViewModel;
import com.friendroid.firebasemvvm.module.Registration.view.RegistrationActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements LoginContract.SignUp {

    private ActivityLoginBinding loginBinding;
    private LoginViewModel viewModel;
    static final int GOOGLE_SIGN_IN = 123;
    GoogleSignInClient mGoogleSigninClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSigninClient = GoogleSignIn.getClient(this,googleSignInOptions);


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

        loginBinding.googlesignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signInGoogle();
            }
        });

    }

    @Override
    public void gotosignup(View view) {
        startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
    }


    void signInGoogle()
    {
        loginBinding.progressCircular.setVisibility(View.VISIBLE);
        Intent signInIntent = mGoogleSigninClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_SIGN_IN)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try
            {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null)
                {
                    viewModel.firebaseAuthWithGoogle(account,loginBinding.progressCircular);
                }

            }
            catch (ApiException e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.updateUI(this);
    }
}
