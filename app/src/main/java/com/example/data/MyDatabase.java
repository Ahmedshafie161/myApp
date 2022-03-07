package com.example.data;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.pojo.PostModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MyDatabase {
    DatabaseReference myRef ;

    //initialize database, get reference ,register listener read data changes
    public void initMyDatabase (){

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://myapp-8124e-default-rtdb.europe-west1.firebasedatabase.app");
        // get root object of firebase database
        this.myRef = database.getReference().child("User");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               // Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                PostModel postModel =  dataSnapshot.getValue(PostModel.class);
                Log.d(TAG, "Value is: " + postModel.getTitle());
                //Log.d(TAG, "Value is: " + map);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());

            }
        });
    }

    // Write a message to the database

    public void addToDatabase(Context context,String body) {

        //String key =myRef.push().getKey();
        PostModel postModel = new PostModel(3,body,"aluya");


         //myRef.child(key).setValue(postModel)
        myRef.setValue(postModel)
                .addOnSuccessListener((Activity) context, unused -> Log.d("FirebaseData","user data uploaded successfully"))
                .addOnFailureListener((Activity) context, e -> Log.d("FirebaseData","user data upload failed"));

    }
}
