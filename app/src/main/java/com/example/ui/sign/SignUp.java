package com.example.ui.sign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.example.R;
import com.example.databinding.ActivitySignUpBinding;
import com.example.ui.main.PostViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    ActivitySignUpBinding binding ;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // View Binding
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // FireBase Auth
        mAuth=FirebaseAuth.getInstance();
        // button listener sign up auth
        binding.btnSignup.setOnClickListener(v -> { mAuth.createUserWithEmailAndPassword(binding.eTName.getText().toString(),binding.eTPassword.getText().toString());

        });

    }
}