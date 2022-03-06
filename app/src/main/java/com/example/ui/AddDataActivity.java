package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.R;
import com.example.pojo.MyAggregateData;
import com.example.pojo.PostModel;

public class AddDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        Toast.makeText(getApplicationContext(),"hellooo",Toast.LENGTH_LONG).show();

        EditText etName=findViewById(R.id.addname);
        EditText etPost=findViewById(R.id.addfollow);
        EditText etFollow=findViewById(R.id.addpost);
        Button btnAdd = findViewById(R.id.btn_addData);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = etName.getText().toString();
                    int postNumb = Integer.parseInt(etPost.getText().toString());
                    int followNumb = Integer.parseInt(etFollow.getText().toString());

                    MyAggregateData myAggregateData= new MyAggregateData(name,followNumb,postNumb);

                    Intent intent = new Intent();
                    intent.putExtra("agr",myAggregateData);
                    setResult(RESULT_OK,intent);
                    finish();
                }catch (Exception e){
                    Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();


                }


            }
        });

    }
}