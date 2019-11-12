package com.friendroid.firebasemvvm.module.viewstudent.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.friendroid.firebasemvvm.base.basemodel.Student;
import com.friendroid.firebasemvvm.module.viewstudent.contract.StudentViewInterface;
import com.friendroid.firebasemvvm.module.viewstudent.repository.ViewStudentDataRespository;

import java.util.List;

public class ViewStudentViewModel extends AndroidViewModel implements StudentViewInterface.viewstudent {

    private ViewStudentDataRespository respository;

    public ViewStudentViewModel(@NonNull Application application) {
        super(application);
        respository = new ViewStudentDataRespository(application);
    }


    @Override
    public LiveData<List<Student>> getAllstudentdatafromdb() {
        return respository.getMutableLiveData();
    }
}
