package com.friendroid.firebasemvvm.module.viewstudent.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.friendroid.firebasemvvm.R;
import com.friendroid.firebasemvvm.base.basemodel.Student;
import com.friendroid.firebasemvvm.databinding.ActivityStudentBinding;
import com.friendroid.firebasemvvm.module.viewstudent.adapter.StudentAdapter;
import com.friendroid.firebasemvvm.module.viewstudent.viewmodel.ViewStudentViewModel;

import java.util.List;

public class StudentActivity extends AppCompatActivity {


    private ViewStudentViewModel viewModel;
    private StudentAdapter adapter;
    private RecyclerView recyclerView;
    private ActivityStudentBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_student);
        recyclerView = mainBinding.myrecyclerview;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = ViewModelProviders.of(this).get(ViewStudentViewModel.class);
        viewModel.getAllstudentdatafromdb().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {

                adapter = new StudentAdapter(students,StudentActivity.this);
                recyclerView.setAdapter(adapter);

            }
        });
    }
}
