package com.friendroid.firebasemvvm.module.Registration.viewmodel;

import android.app.Application;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.friendroid.firebasemvvm.module.Registration.contract.RegistrationContract;
import com.friendroid.firebasemvvm.module.Registration.model.RegistrationModel;
import com.friendroid.firebasemvvm.module.Registration.repository.RegistrationRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationViewModel extends AndroidViewModel implements RegistrationContract.auth, RegistrationContract.userdata {

    private RegistrationRepository repository;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("User");

    public RegistrationViewModel(@NonNull Application application) {
        super(application);
        repository = new RegistrationRepository(application, databaseReference);
    }

    @Override
    public void SignUpCredential(String email, String password, ProgressBar progressBar) {
        repository.SignUpWithUserEmailAndPassword(email, password, progressBar);
    }


    @Override
    public void addUserData(RegistrationModel registrationModel) {

        String id = databaseReference.push().getKey();
        repository.addUser(id, registrationModel);

    }
}
