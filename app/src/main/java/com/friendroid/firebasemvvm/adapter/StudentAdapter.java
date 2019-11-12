package com.friendroid.firebasemvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.friendroid.firebasemvvm.R;
import com.friendroid.firebasemvvm.databinding.CustomStudentBinding;
import com.friendroid.firebasemvvm.model.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.EmployeeViewHolder> {

    private List<Student> students;
    private Context context;

    public StudentAdapter(List<Student> students, Context context) {
        this.students = students;
        this.context = context;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        CustomStudentBinding studentBinding =
                DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                        R.layout.custom_student, viewGroup, false);
        return new EmployeeViewHolder(studentBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder employeeViewHolder,final int i) {
        Student student = students.get(i);
        employeeViewHolder.studentBinding.setStudent(student);
        employeeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Clicked on "+ students.get(i).getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (students != null) {
            return students.size();
        } else {
            return 0;
        }
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        // below class name will like custom layout with binding keyword
        private CustomStudentBinding studentBinding;

        EmployeeViewHolder(@NonNull CustomStudentBinding studentBinding) {
            super(studentBinding.getRoot());
            this.studentBinding = studentBinding;
        }
    }
}
