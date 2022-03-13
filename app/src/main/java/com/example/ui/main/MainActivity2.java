package com.example.ui.main;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.BlankFragment;
import com.example.BlankFragment2;
import com.example.R;
import com.example.RecyclerItemListener;
import com.example.pojo.MyAggregateData;
import com.example.pojo.PostModel;
import com.example.ui.AddDataActivity;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements BlankFragment2.MyFragmentListener {

    final int REQ_CODE_APP = 0 ;
    Button btnLoop;
    MyHandler myHandler;
    MyRecyclerAdapter myRecyclerAdapter;
    MyAggregateData arrayAggr;
    /* Data from the authenticated user */
    private static final String CHANNEL_ID = "simplified_coding";
    private static final String CHANNEL_NAME = "simplified_coding";
    private static final String CHANNEL_DESC = "simplified_coding Notification";
    PostViewModel postViewModel ;
    private PostModel postModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //inflate
        Button btnAdd= findViewById(R.id.btn_gotoadd);
        RecyclerView lv1=findViewById(R.id.recycler_view);
        btnLoop = findViewById(R.id.btn_loop);
        Button btnFrag1 = findViewById(R.id.button4);
        Button btnFrag2 = findViewById(R.id.button5);
        //arraylist data
        ArrayList<MyAggregateData>  al= new ArrayList<>();
        for (int i =0; i<50 ; i++){
            al.add(new MyAggregateData("ahmed",1,10));
        }


        //MVVM implemntation
        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);
        // retrofit
        postViewModel.getPosts();
        // firebase
        postViewModel.addToDatabase(new PostModel(3,"main2","main1"));
        Log.d("TAG", "value added froma activity");

        //recycler adapter + listener
        myRecyclerAdapter = new MyRecyclerAdapter(al, new RecyclerItemListener() {
            @Override
            public void onItemClick(MyAggregateData arrayAggr) {
                MainActivity2.this.arrayAggr =arrayAggr;

                Toast.makeText(MainActivity2.this,arrayAggr.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(PostModel postModel) {
                MainActivity2.this.postModel =postModel;

                Toast.makeText(MainActivity2.this,postModel.getTitle(), Toast.LENGTH_SHORT).show();

            }
        });
        //lv1.setHasFixedSize(true);

        lv1.setLayoutManager(new LinearLayoutManager(this));
        lv1.setAdapter(myRecyclerAdapter);
        //lv1.setAdapter(adapter);

        // update recyclerview when data change
        postViewModel.postsMutableLiveData.observe(this, postModels -> myRecyclerAdapter.setList(postModels));
        //not lambda
        /*postViewModel.postsMutableLiveData.observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels) {
                myRecyclerAdapter.setList(postModels);

            }
        });*/
/*

        myHandler = new MyHandler();


        // AsyncTask
        /*MyAsycTask myAsycTask= new MyAsycTask();
        myAsycTask.execute("ahmed");*/

        //handler instantiation
        Handler handler = new Handler();


        Toast.makeText(MainActivity2.this,"toaaast",Toast.LENGTH_LONG).show();


        // navigation return result and assign result to aggregateDate, send it to Adapter
        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>()  {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // There are no request codes
                            Intent data = result.getData();
                            MyAggregateData myAggregateData=(MyAggregateData) data.getSerializableExtra("agr");
                            myRecyclerAdapter.addItem(myAggregateData);

                            Toast.makeText(getBaseContext(),"added Value",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //thread + send handler to ui
        Thread t1 = new Thread(() -> {

            for (int i = 0; i < 10000; i++) {
                System.out.println("this is loop "+i);
                // Toast.makeText(getBaseContext(),"this is loop ",Toast.LENGTH_LONG);
                int finalI = i;


                // handler post send data
                /*
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        btnLoop.setText("loop number"+String.valueOf(finalI));

                    }
                }); */
                //handler.sendMessage
                        Message msg = new Message();
                        msg.arg1 =i ;

                        myHandler.sendMessage(msg);
                //view.Post
                        /*
                        btnLoop.post(new Runnable() {
                            @Override
                            public void run() {
                                btnLoop.setText("this is loop "+ finalI);
                            }
                        });
                        */
                //runOnUIThread
                        /*runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Toast.makeText(getBaseContext(),"this is loop ",Toast.LENGTH_LONG);
                                btnLoop.setText("loop :"+ finalI);

                            }
                        });*/
                //sleep
                try {
                    Thread.sleep(2000) ;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        //button listener activity navigation  AddData Class
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), AddDataActivity.class);
            //startActivityForResult(intent,REQ_CODE_APP);
            someActivityResultLauncher.launch(intent);


        });

        //button loop handler
        btnLoop.setOnClickListener(v -> {
            t1.start();
            //Start thread 2

        });

        //fragment navigation + send data from activity to fragment
        btnFrag1.setOnClickListener(v -> {
            //fragment
            //old way  no encapsulation
            /*FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction(); //in same time
            BlankFragment fragment = new BlankFragment();
            Bundle bundle = new Bundle();
            bundle.putString("param1",arrayAggr.getName());
            fragment.setArguments(bundle);
            fragmentTransaction.replace(R.id.fragment_container,fragment);//replace frame with fragment
            fragmentTransaction.addToBackStack(null); //when press back , back  fragment movements
            fragmentTransaction.commit(); //start */

            // Fragment better way , no working with bundle send data from activity to fragment
            BlankFragment blankFragment = BlankFragment.newInstance(arrayAggr.getName(),null);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction(); //in same time
            fragmentTransaction.replace(R.id.fragment_container,blankFragment);//replace frame with fragment
            fragmentTransaction.commit(); //start */

        });

        //fragment  navigation + Trigger notification
        btnFrag2.setOnClickListener(v -> {
            //fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction(); //in same time
            BlankFragment2 fragment = new BlankFragment2();
            fragmentTransaction.replace(R.id.fragment_container,fragment);//replace frame with fragment
            fragmentTransaction.addToBackStack(null); //when press back , back  fragment movements
            fragmentTransaction.commit(); //start
            displayNotification();

        });
    }
        //Recieving data from FRAGMENT


    @Override
    protected void onStart() {
       super.onStart();
        /* if(mAuth.getCurrentUser()==null){
            startActivity(new Intent(this, SignIn.class));
        } */
    }

    @Override
    public void onFragmentInteraction(int x) {
        Toast.makeText(this, String.valueOf(x)+"This is it", Toast.LENGTH_SHORT).show();
        btnLoop.setText("love hosny");
    }
        //AsyncTask
    class MyAsycTask extends AsyncTask <String,Integer,Double> {


        @Override
        protected Double doInBackground(String... strings) {
            publishProgress(1);
            btnLoop.setText("ahmed");
            return null;
        }

        @Override
        protected void onPostExecute(Double unused) {
            super.onPostExecute(unused);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }

    //recieving message from looper handler
    private class MyHandler extends Handler{

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            btnLoop.setText(String.valueOf(msg.arg1));
        }
    }

    //Notification Decleration
    private void displayNotification (){
        //check version 26 then do channel
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("my Channel");
            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);
        }
        Intent intent = new Intent(this , MainActivity2.class);
        PendingIntent pi = PendingIntent.getActivity(this , 0 , intent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext(),CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("my Title")
                .setContentText("my text")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.drawable.ic_launcher_background,"rePlay",pi); //clickable button
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,builder.build());
    }
}
    //navigation depricated
      /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         if(requestCode==REQ_CODE_APP && resultCode==RESULT_OK){
            MyAggregateData myAggregateData=(MyAggregateData) data.getSerializableExtra("agr");
            myAdpater.addItem(myAggregateData);
            myAdpater.notifyDataSetChanged();
         }


    } */

