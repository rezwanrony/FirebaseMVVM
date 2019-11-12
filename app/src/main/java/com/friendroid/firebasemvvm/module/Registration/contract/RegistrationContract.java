package com.friendroid.firebasemvvm.module.Registration.contract;

import android.widget.ProgressBar;

import com.friendroid.firebasemvvm.module.Registration.model.RegistrationModel;

public interface RegistrationContract {

    interface auth {

        void SignUpCredential(String email, String password, ProgressBar progressBar);
    }

    interface userdata{

        void addUserData(RegistrationModel registrationModel);

    }


}
