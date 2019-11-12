package com.friendroid.firebasemvvm.module.Registration.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.friendroid.firebasemvvm.R;
import com.friendroid.firebasemvvm.databinding.ActivityRegistrationBinding;
import com.friendroid.firebasemvvm.module.Login.viewmodel.LoginViewModel;
import com.friendroid.firebasemvvm.module.Registration.model.RegistrationModel;
import com.friendroid.firebasemvvm.module.Registration.viewmodel.RegistrationViewModel;

public class RegistrationActivity extends AppCompatActivity {

    private ActivityRegistrationBinding binding;
    private RegistrationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_registration);

        viewModel = ViewModelProviders.of(this).get(RegistrationViewModel.class);
        binding.registrationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.remail.getText().toString();
                String password = binding.rpassword.getText().toString();
                viewModel.SignUpCredential(email,password, binding.rprogressCircular);
                viewModel.addUserData(new RegistrationModel(email,password));
            }
        });


    }
}
