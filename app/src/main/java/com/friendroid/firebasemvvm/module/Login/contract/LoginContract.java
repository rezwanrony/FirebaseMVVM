package com.friendroid.firebasemvvm.module.Login.contract;

import android.view.View;
import android.widget.ProgressBar;

import com.friendroid.firebasemvvm.base.basemodel.Student;

public interface LoginContract {

    interface auth {

        void loginCredential(String email, String password, ProgressBar progressBar);

    }

    interface SignUp{

        void gotosignup(View view);

    }


}
