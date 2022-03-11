package com.example.ui.sign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.data.MyDatabase;
import com.example.databinding.ActivitySignUpBinding;
import com.example.pojo.PostModel;
import com.example.ui.main.MainActivity2;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
            myDatabase.initMyDatabaseAuth(binding.etEmail.getText().toString(),binding.eTPassword.getText().toString(),SignUp.this,postModel);
            startActivity(new Intent(SignUp.this,MainActivity2.class));

        });
    }
}