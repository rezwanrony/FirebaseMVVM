package com.friendroid.firebasemvvm.module.addstudent.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.friendroid.firebasemvvm.base.basemodel.Student;
import com.friendroid.firebasemvvm.module.addstudent.contract.StudentAddInterface;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;

public class AddStudentRepository implements StudentAddInterface.StudentRepo{

    private DatabaseReference databaseReference;
    private Context context;

    public AddStudentRepository(DatabaseReference databaseReference, Context context) {
        this.databaseReference = databaseReference;
        this.context = context;
    }


    @Override
    public void addStudent(String id, Student student) {
        databaseReference.child(id).setValue(student).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context, "Successfully added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Add Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
