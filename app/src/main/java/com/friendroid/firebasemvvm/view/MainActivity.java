package com.friendroid.firebasemvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.friendroid.firebasemvvm.R;
import com.friendroid.firebasemvvm.databinding.ActivityMainBinding;
import com.friendroid.firebasemvvm.model.Student;
import com.friendroid.firebasemvvm.viewmodel.MainActivityViewModel;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    MainActivityViewModel viewModel;
    String name,email,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        FirebaseApp.initializeApp(MainActivity.this);
        viewModel= ViewModelProviders.of(this).get(MainActivityViewModel.class);

        activityMainBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=activityMainBinding.etName.getText().toString();
                email=activityMainBinding.etEmail.getText().toString();
                phone=activityMainBinding.etPhone.getText().toString();
                addStudent(name,email,phone);
            }
        });

        viewModel.getStudentData(MainActivity.this).observe(this, new Observer<DataSnapshot>() {
            @Override
            public void onChanged(DataSnapshot dataSnapshot) {
                final List<Student>studentList=new ArrayList<Student>();
                        if (dataSnapshot!=null) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                String name = ds.child("name").getValue(String.class);
                                String email = ds.child("email").getValue(String.class);
                                String phone = ds.child("phone").getValue(String.class);
                                studentList.add(new Student(name,email,phone));
                            }

                            Toast.makeText(MainActivity.this, String.valueOf(studentList.size()), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void addStudent(String name,String email,String phone){
        viewModel.addstudentData(new Student(name,email,phone));
    }
}
