package com.friendroid.firebasemvvm.module.addstudent.contract;

import com.friendroid.firebasemvvm.base.basemodel.Student;

public interface StudentAddInterface {

    interface StudentRepo{

        void addStudent(String id, Student student);

    }


}
