package com.friendroid.firebasemvvm.module.addstudent.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.friendroid.firebasemvvm.base.basemodel.Student;
import com.friendroid.firebasemvvm.module.addstudent.repository.AddStudentRepository;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudentViewModel extends AndroidViewModel {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Student");
    AddStudentRepository studentRepository;

    public AddStudentViewModel(@NonNull Application application) {
        super(application);
        studentRepository=new AddStudentRepository(databaseReference,application);
    }

    public void addstudentData(Student student){
        String id=databaseReference.push().getKey();
        studentRepository.addStudent(id,student);
    }

}
