package com.friendroid.firebasemvvm.networking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.friendroid.firebasemvvm.model.Student;
import com.google.firebase.database.DataSnapshot;

import java.util.List;

public interface DataRepository {

    interface StudentRepo{

        void addStudent(String id,Student student);
        LiveData<DataSnapshot>getStudentData();
    }


}
