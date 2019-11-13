package com.friendroid.firebasemvvm.module.Login.repository;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.friendroid.firebasemvvm.module.addstudent.view.MainActivity;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;

import java.util.HashMap;
import java.util.concurrent.Executor;

public class FacebookLoginRepository {
    private DatabaseReference databaseReference;
    private Context context;
    private String first_name="",last_name="",email="",id="",image_url="",userid="";
    FirebaseAuth mAuth;
    public FacebookLoginRepository(Context context) {
        this.context = context;
        databaseReference=FirebaseDatabase.getInstance().getReference();
        mAuth=FirebaseAuth.getInstance();

    }

    public void handleFacebookAccessToken(Activity activity,AccessToken token) {
        Log.d("7token",AccessToken.getCurrentAccessToken().getToken().toString());
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(activity, task -> {
            if (task.isSuccessful()) {
                activity.startActivity(new Intent(activity, MainActivity.class));
                getUserProfile(token,mAuth.getCurrentUser().getUid());
            } else {
                Toast.makeText(activity, "Authentication failed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //TBL_RON: 14/10/2019 get user profile information
    public void getUserProfile(AccessToken currentAccessToken,String uid) {
        userid=uid;
        GraphRequest request = GraphRequest.newMeRequest(
                currentAccessToken, (object, response) -> {

                    try {
                        Toast.makeText(context, "Welcome " + object.getString("first_name"), Toast.LENGTH_SHORT).show();
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        first_name = object.getString("first_name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        last_name = object.getString("last_name");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        email = object.getString("email");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        id = object.getString("id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";

                    HashMap<Object, String> hashMap = new HashMap<>();
                    hashMap.put("email", email);
                    hashMap.put("firstname", first_name);
                    hashMap.put("lastname", last_name);
                    hashMap.put("address", "");
                    hashMap.put("authID", userid);
                    hashMap.put("avatar", image_url);
                    hashMap.put("city", "");
                    hashMap.put("createdat", "13/11/2019");
                    hashMap.put("deviceToken", "");
                    hashMap.put("phonenumber", "");
                    hashMap.put("state", "");
                    hashMap.put("usertype", "");
                    hashMap.put("zipcode", "");
                    FirebaseDatabase database = FirebaseDatabase.getInstance();

                    databaseReference.child("users").child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            // If data exists, toast currently stored value. Otherwise, set value to "Present"
                            if (dataSnapshot.exists()) {
                                Log.d("firebaseuserdata","User already exist");
                            } else {
                                databaseReference.child("users").child(userid).setValue(hashMap);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            //TODO: Handle this
                        }
                    });

                });


        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();
    }


}
