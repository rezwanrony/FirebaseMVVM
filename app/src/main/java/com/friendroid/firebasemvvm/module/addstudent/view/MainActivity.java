package com.friendroid.firebasemvvm.module.addstudent.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.friendroid.firebasemvvm.R;
import com.friendroid.firebasemvvm.base.basemodel.Student;
import com.friendroid.firebasemvvm.databinding.ActivityMainBinding;
import com.friendroid.firebasemvvm.module.Login.view.LoginActivity;
import com.friendroid.firebasemvvm.module.addstudent.viewmodel.AddStudentViewModel;
import com.friendroid.firebasemvvm.module.viewstudent.view.StudentActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    AddStudentViewModel viewModel;
    String name, email, phone;
    private GoogleSignInClient mGoogleSigninClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                .Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSigninClient = GoogleSignIn.getClient(this, googleSignInOptions);


        FirebaseApp.initializeApp(MainActivity.this);
        viewModel = ViewModelProviders.of(this).get(AddStudentViewModel.class);

        activityMainBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = activityMainBinding.etName.getText().toString();
                email = activityMainBinding.etEmail.getText().toString();
                phone = activityMainBinding.etPhone.getText().toString();
                addStudent(name, email, phone);
            }
        });

    }

    private void addStudent(String name, String email, String phone) {
        viewModel.addstudentData(new Student(name, email, phone));
    }

    public void GoStudentList(View view) {

        startActivity(new Intent(MainActivity.this, StudentActivity.class));
    }

    public void gotoLogout(View view) {

        FirebaseAuth.getInstance().signOut();
        mGoogleSigninClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


    }
}
