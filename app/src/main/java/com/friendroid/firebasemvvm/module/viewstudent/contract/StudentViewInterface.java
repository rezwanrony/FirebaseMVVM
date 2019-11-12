package com.friendroid.firebasemvvm.module.viewstudent.contract;

import androidx.lifecycle.LiveData;

import com.friendroid.firebasemvvm.base.basemodel.Student;

import java.util.List;

public interface StudentViewInterface {


    interface viewstudent{


        LiveData<List<Student>> getAllstudentdatafromdb();

    }


}
