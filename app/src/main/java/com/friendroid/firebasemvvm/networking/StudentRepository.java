package com.friendroid.firebasemvvm.networking;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.friendroid.firebasemvvm.model.Student;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.List;

public class StudentRepository implements DataRepository.StudentRepo{

    public DatabaseReference databaseReference;
    public Context context;
    FirebaseQueryLiveData liveData;

    public StudentRepository(DatabaseReference databaseReference, Context context) {
        this.databaseReference = databaseReference;
        this.context = context;
        liveData = new FirebaseQueryLiveData(databaseReference);
    }


    @Override
    public void addStudent(String id,Student student) {
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

    @NonNull
    public LiveData<DataSnapshot> getStudentData() {
        return liveData;
    }
}
