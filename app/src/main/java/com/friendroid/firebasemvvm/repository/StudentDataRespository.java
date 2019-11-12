package com.friendroid.firebasemvvm.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.friendroid.firebasemvvm.model.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentDataRespository  {


    private List<Student> studentList = new ArrayList<>();
    private MutableLiveData<List<Student>> mutableLiveData = new MutableLiveData<>();
    private Context context;
    private DatabaseReference database;

    public StudentDataRespository(Context context) {
        this.context = context;
    }


    public MutableLiveData<List<Student>> getMutableLiveData() {
        database = FirebaseDatabase.getInstance().getReference();
        database.child("Student").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = ds.child("name").getValue(String.class);
                    String email = ds.child("email").getValue(String.class);
                    String phone = ds.child("phone").getValue(String.class);
                    studentList.add(new Student(name, email, phone));
                    mutableLiveData.setValue(studentList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(context, "Error for " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        return mutableLiveData;
    }
}
