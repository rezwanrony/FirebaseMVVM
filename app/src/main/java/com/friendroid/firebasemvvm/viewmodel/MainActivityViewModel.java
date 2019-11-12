package com.friendroid.firebasemvvm.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.friendroid.firebasemvvm.model.Student;
import com.friendroid.firebasemvvm.networking.StudentRepository;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Student");
    StudentRepository studentRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        studentRepository=new StudentRepository(databaseReference,application);
    }

    public void addstudentData(Student student){
        String id=databaseReference.push().getKey();
        studentRepository.addStudent(id,student);
    }

    public LiveData<DataSnapshot>getStudentData(Context context){
        return studentRepository.getStudentData();
    }

}
