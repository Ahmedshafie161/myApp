package com.example.ui.sign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.data.MyDatabase;
import com.example.databinding.ActivitySignInBinding;
import com.example.ui.main.MainActivity2;

public class SignIn extends AppCompatActivity {
    ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //sign IN
        binding.btnSignIn.setOnClickListener(v -> {
            MyDatabase myDatabase= new MyDatabase();
            if ( myDatabase.signIn(binding.eTEmail.getText().toString(),binding.eTPassword.getText().toString())== true){
            startActivity(new Intent(SignIn.this,MainActivity2.class));
            }
        });


        //sign Up
        binding.btnSignUp.setOnClickListener(v->{
            startActivity(new Intent(SignIn.this,SignUp.class));});


}}