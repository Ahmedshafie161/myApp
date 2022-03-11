package com.example.data;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.os.health.PackageHealthStats;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.pojo.PostModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;

public class MyDatabase {
    FirebaseAuth firebaseAuth ;
    DatabaseReference databaseReference;


    //initialize database, get reference ,register listener read data changes
    /* list of pojo
        public void initMyDatabase ( ){
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://myapp-8124e-default-rtdb.europe-west1.firebasedatabase.app");
        // get root object of firebase database
        this.databaseReference = database.getReference().child("User");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<PostModel> postModelList= new LinkedList<PostModel>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    postModelList.add(postSnapshot.getValue(PostModel.class));
                }
                Log.i(TAG, "list value is "+String.valueOf(postModelList));
                Log.i(TAG, "first element value is "+String.valueOf(postModelList.get(0).getTitle()));
                //Log.d("hay", "Value is: "+postModel.getTitle() );
                //Log.d(TAG, "Value is: " + map);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());

            }
        });
    }*/
    public void initDatabase (String fbUid){
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://myapp-8124e-default-rtdb.europe-west1.firebasedatabase.app");
        // get root object of firebase database
        this.databaseReference = database.getReference().child(fbUid);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                PostModel postModel =  dataSnapshot.getValue(PostModel.class);

                /*List<PostModel> postModelList= new LinkedList<PostModel>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    postModelList.add(postSnapshot.getValue(PostModel.class));
                }
                Log.i(TAG, "list value is "+String.valueOf(postModelList));
                Log.i(TAG, "first element value is "+String.valueOf(postModelList.get(0).getTitle()));
                //Log.d("hay", "Value is: "+postModel.getTitle() );
                //Log.d(TAG, "Value is: " + map);*/
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());

            }
        });
    }

    public void initMyDatabaseAuth (String email, String password,Context context, PostModel postModel){
        firebaseAuth =FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                //firebaseUser=task.getResult().getUser();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                initDatabase(firebaseUser.getUid());
                addToDatabase(context ,postModel);
            }
        });
    }

    // Write a message to the database


    public void addToDatabase(Context context, PostModel postModel) {

        //String key =myRef.push().getKey();
         //myRef.child(key).setValue(postModel)
        //databaseReference.push().setValue(postModel)
        databaseReference.setValue(postModel)
                .addOnSuccessListener((Activity) context, unused -> Log.d("TAG","user data uploaded successfully"))
                .addOnFailureListener((Activity) context, e -> Log.d("TAG","user data upload failed"));

    }
}
