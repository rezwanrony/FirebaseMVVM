package com.friendroid.firebasemvvm.module.Login.view;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
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
import com.google.firebase.auth.FirebaseAuth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements LoginContract.SignUp {

    private ActivityLoginBinding loginBinding;
    private LoginViewModel viewModel;
    private CallbackManager callbackManager;
    private String TAG = "FacebookLogin";
    static final int GOOGLE_SIGN_IN = 123;
    private GoogleSignInClient mGoogleSigninClient;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        initialize();

        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSigninClient = GoogleSignIn.getClient(this, googleSignInOptions);


        loginBinding.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginBinding.email.getText().toString();
                String password = loginBinding.password.getText().toString();
                viewModel.loginCredential(email, password, loginBinding.progressCircular);
            }
        });

        loginBinding.signuptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotosignup(v);
            }
        });

        callbackManager = CallbackManager.Factory.create();

        loginBinding.loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));

        loginBinding.loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "facebook:onSuccess:" + loginResult);
                Log.d("mytoken",loginResult.getAccessToken().toString());
                viewModel.signinwithfacebook(LoginActivity.this,loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG, "facebook:onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG, "facebook:onError", error);
            }
        });

    }

    private void initialize() {
        mAuth=FirebaseAuth.getInstance();
        loginBinding.googlesignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signInGoogle();
            }
        });

    }

    @Override
    public void gotosignup(View view) {
        startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
    }


    void signInGoogle() {
        loginBinding.progressCircular.setVisibility(View.VISIBLE);
        Intent signInIntent = mGoogleSigninClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    viewModel.firebaseAuthWithGoogle(account, loginBinding.progressCircular);
                }

            } catch (ApiException e) {
                e.printStackTrace();
            }

        }
        else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewModel.updateUI(this, mAuth);
    }

    public void onClickFacebookButton(View view) {

        if (view==loginBinding.imgFacebookLogin){

            loginBinding.loginButton.performClick();
        }
    }


}
