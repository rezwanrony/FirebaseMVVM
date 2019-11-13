package com.friendroid.firebasemvvm.module.Login.contract;

import android.view.View;
import android.widget.ProgressBar;


import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface LoginContract {

    interface auth {

        void loginCredential(String email, String password, ProgressBar progressBar);

    }

    interface SignUp{

        void gotosignup(View view);

    }

    interface googleSignIn {

        void firebaseAuthWithGoogle(GoogleSignInAccount account, ProgressBar progressBar);

    }


}
