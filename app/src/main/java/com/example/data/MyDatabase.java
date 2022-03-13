package com.example.data;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.pojo.PostModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyDatabase {
    DatabaseReference databaseReference;
    public Boolean isSigned=true;
    private Application application;

    private FirebaseAuth firebaseAuth;
    private MutableLiveData<FirebaseUser> userLiveData;
    private MutableLiveData<Boolean> loggedOutLiveData;
    public MyDatabase(){

        this.application = application;
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.userLiveData = new MutableLiveData<>();
        this.loggedOutLiveData = new MutableLiveData<>();

        if (firebaseAuth.getCurrentUser() != null) {
            userLiveData.postValue(firebaseAuth.getCurrentUser());
        }

    }
    public MyDatabase(Application application) {
        this.application = application;
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.userLiveData = new MutableLiveData<>();
        this.loggedOutLiveData = new MutableLiveData<>();

        if (firebaseAuth.getCurrentUser() != null) {
            userLiveData.postValue(firebaseAuth.getCurrentUser());
            loggedOutLiveData.postValue(false);
        }
    }


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

    public void initDatabase (){
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://myapp-8124e-default-rtdb.europe-west1.firebasedatabase.app");
        String userId=FirebaseAuth.getInstance().getCurrentUser().getUid();

        // get root object of firebase database
        this.databaseReference = database.getReference().child(userId);

    }
    public void getData(){
        // if initl not called , call it , recieve data with listener
        if (databaseReference==null){
            initDatabase();
        }
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                PostModel postModel =  dataSnapshot.getValue(PostModel.class);
                Log.d(TAG, "Data Changed: "+postModel);

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
                Log.d("TAG", "Failed to read value.", error.toException());

            }
        });

    }

    public void signUpAuth(String email, String password,  PostModel postModel){
        FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();
        //create auth , if success add pojo to database
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                //firebaseUser=task.getResult().getUser();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                //userId =firebaseUser.getUid();
                addToDatabase(postModel);
                userLiveData.postValue(firebaseAuth.getCurrentUser());

            }
        });
    }

    // Write a message to the database

    public  void addFromActivity (PostModel postModel){
        initDatabase();
        databaseReference.push().setValue(postModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("TAG", "valued added from activity Succeeded");
                    }
                });
    }
    public void addToDatabase(PostModel postModel) {
        //String key =myRef.push().getKey();
         //myRef.child(key).setValue(postModel)
        //databaseReference.push().setValue(postModel)
        if (databaseReference==null){
            initDatabase();
        }
        databaseReference.setValue(postModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("TAG", "valued added Succeeded");
                    }
                });

    }
    public void signIn(String email , String password){
        FirebaseAuth firebaseAuth =FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            userLiveData.postValue(firebaseAuth.getCurrentUser());
                            Log.d("TAG", "loging Succeeded");
                        }else {
                            Log.e("TAG", "login Failed", task.getException());

                        }
                    });
    }
    public void signOut(){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        loggedOutLiveData.postValue(true);


    }
    public MutableLiveData<Boolean> getUserLoggedMutableLiveData() {
        return loggedOutLiveData;
    }public  MutableLiveData<FirebaseUser> getFirebaseUserMutableLiveData() {
        return userLiveData ;
    }


}
