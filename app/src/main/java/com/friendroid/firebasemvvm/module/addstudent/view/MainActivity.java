package com.friendroid.firebasemvvm.module.addstudent.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.friendroid.firebasemvvm.R;
import com.friendroid.firebasemvvm.base.basemodel.Student;
import com.friendroid.firebasemvvm.databinding.ActivityMainBinding;
import com.friendroid.firebasemvvm.module.addstudent.viewmodel.AddStudentViewModel;
import com.friendroid.firebasemvvm.module.viewstudent.view.StudentActivity;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    AddStudentViewModel viewModel;
    String name,email,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        FirebaseApp.initializeApp(MainActivity.this);
        viewModel= ViewModelProviders.of(this).get(AddStudentViewModel.class);

        activityMainBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=activityMainBinding.etName.getText().toString();
                email=activityMainBinding.etEmail.getText().toString();
                phone=activityMainBinding.etPhone.getText().toString();
                addStudent(name,email,phone);
            }
        });

    }

    private void addStudent(String name,String email,String phone){
        viewModel.addstudentData(new Student(name,email,phone));
    }

    public void GoStudentList(View view) {

        startActivity(new Intent(MainActivity.this, StudentActivity.class));
    }
}
