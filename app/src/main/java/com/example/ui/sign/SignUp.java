package com.example.ui.sign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.data.MyDatabase;
import com.example.databinding.ActivitySignUpBinding;
import com.example.pojo.PostModel;
import com.example.ui.main.MainActivity2;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    ActivitySignUpBinding binding ;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // View Binding
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // FireBase Auth
        firebaseAuth =FirebaseAuth.getInstance();
        // button listener sign up auth
        binding.btnSignup.setOnClickListener(v -> {
            MyDatabase myDatabase = new MyDatabase();
            PostModel postModel = new PostModel(3,binding.etBody.getText().toString(),binding.etTitle.getText().toString());
            myDatabase.signUpAuth(binding.etEmail.getText().toString(),binding.eTPassword.getText().toString(),postModel);
            startActivity(new Intent(SignUp.this,MainActivity2.class));

        });

    }
}