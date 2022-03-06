package com.example.data;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyDatabase {
    // Write a message to the database
    public void addToDatabase(Context context, String key , String value){
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://myapp-8124e-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference myRef = database.getReference();
        myRef.child(key)
                .setValue(value)
                .addOnSuccessListener((Activity) context, unused -> Log.d("FirebaseData","user data uploaded successfully"))
                .addOnFailureListener((Activity) context, e -> Log.d("FirebaseData","user data upload failed"));

    }
}
