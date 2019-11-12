package com.friendroid.firebasemvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.friendroid.firebasemvvm.model.Student;
import com.friendroid.firebasemvvm.contracts.DataRepository;
import com.friendroid.firebasemvvm.repository.StudentDataRespository;

import java.util.List;

public class StudentViewModel extends AndroidViewModel implements DataRepository.viewstudent {

    private StudentDataRespository respository;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        respository = new StudentDataRespository(application);
    }


    @Override
    public LiveData<List<Student>> getAllstudentdatafromdb() {
        return respository.getMutableLiveData();
    }
}
