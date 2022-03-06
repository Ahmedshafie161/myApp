package com.example.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.R;

public class MainActivitySec extends AppCompatActivity {
    double area= 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainn);

        //inflate
        Spinner sp=findViewById(R.id.spinner);
        Button btn1=findViewById(R.id.button);
        EditText etlength=findViewById(R.id.etlength);
        EditText etwidth=findViewById(R.id.etwidth);
        EditText etbase=findViewById(R.id.etbase);
        EditText etheight=findViewById(R.id.etheight);
        EditText etradius=findViewById(R.id.etradius);
        TextView tvresult=findViewById(R.id.tvresult);

       //spinner listener
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        etlength.setVisibility(View.GONE);
                        etwidth.setVisibility(View.GONE);
                        etbase.setVisibility(View.GONE);
                        etheight.setVisibility(View.GONE);
                        etradius.setVisibility(View.GONE);
                        Toast.makeText(getBaseContext(),"please select type",Toast.LENGTH_LONG).show();

                        break;

                    case 1:
                        etlength.setVisibility(View.VISIBLE);
                        etwidth.setVisibility(View.VISIBLE);
                        etbase.setVisibility(View.GONE);
                        etheight.setVisibility(View.GONE);
                        etradius.setVisibility(View.GONE);
                        Toast.makeText(getBaseContext(),"U selected RECTANGLE",Toast.LENGTH_SHORT).show();
                        etradius.setText("");
                        etbase.setText("");
                        etheight.setText("");

                        break;


                    case 2:
                        etlength.setVisibility(View.GONE);
                        etwidth.setVisibility(View.GONE);
                        etbase.setVisibility(View.GONE);
                        etheight.setVisibility(View.GONE);
                        etradius.setVisibility(View.VISIBLE);
                        Toast.makeText(getBaseContext(),"U selected CIRCLE",Toast.LENGTH_SHORT).show();
                        etlength.setText("");
                        etwidth.setText("");
                        etbase.setText("");
                        etheight.setText("");

                        break;

                    case 3:
                        etlength.setVisibility(View.GONE);
                        etwidth.setVisibility(View.GONE);
                        etbase.setVisibility(View.VISIBLE);
                        etheight.setVisibility(View.VISIBLE);
                        etradius.setVisibility(View.GONE);
                        Toast.makeText(getBaseContext(),"U selected TRIANGLE",Toast.LENGTH_SHORT).show();
                        etradius.setText("");
                        etlength.setText("");
                        etwidth.setText("");

                        break;


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //button listener
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=sp.getSelectedItemPosition();
                switch(pos) {
                    case 1 :
                        //rectangle
                        double x = Double.parseDouble(etlength.getText().toString());
                        double y = Double.parseDouble(etwidth.getText().toString());
                        tvresult.setText(String.valueOf(x*y));

                        break;
                    case 2 :
                        //circle
                        double xc= Double.parseDouble(etradius.getText().toString());
                        tvresult.setText(String.valueOf(3.14*xc*xc));

                        break;
                    case 3 :
                        //triangle
                        double xb= Double.parseDouble(etbase.getText().toString());
                        double xh= Double.parseDouble(etheight.getText().toString());

                        tvresult.setText(String.valueOf(.5*xb*xh));

                        break;



                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getBaseContext(),"hi onStart",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getBaseContext(),"hi onRestart",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getBaseContext(),"hi onPause",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getBaseContext(),"hi onStop",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getBaseContext(),"hi onResume",Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getBaseContext(),"hi onDestroy",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        TextView tv=findViewById(R.id.tvresult);
        //save Data in bundle

        outState.putDouble("shafie",Double.parseDouble(tv.getText().toString()));


    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Toast.makeText(getBaseContext(),"hi onRestoreInstance",Toast.LENGTH_SHORT).show();
        //inflate view
        TextView tv=findViewById(R.id.tvresult);
        //get saved data and assign it to view attribute
        tv.setText(String.valueOf(savedInstanceState.getDouble("shafie")));




    }

}