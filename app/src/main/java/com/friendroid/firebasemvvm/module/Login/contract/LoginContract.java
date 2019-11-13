package com.friendroid.firebasemvvm.module.Login.contract;

import android.app.Activity;
import android.view.View;
import android.widget.ProgressBar;

import com.facebook.AccessToken;
import com.friendroid.firebasemvvm.base.basemodel.Student;
import com.google.firebase.auth.FirebaseAuth;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public interface LoginContract {

    interface auth {

        void loginCredential(String email, String password, ProgressBar progressBar);

    }

    interface SignUp{

        void gotosignup(View view);

    }

    interface FacebookLogin{
        void signinwithfacebook(Activity activity,AccessToken accessToken);
    }

    interface googleSignIn {

        void firebaseAuthWithGoogle(GoogleSignInAccount account, ProgressBar progressBar);
        void updateUI(Activity activity, FirebaseAuth mAuth);

    }


}
