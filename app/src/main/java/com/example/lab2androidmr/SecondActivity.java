package com.example.lab2androidmr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Display;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity{
    RecyclerView recyclerView;

    public final static String KEY_ARRAYLIST = "com.example.lab2androidmr.arraylist";
    //ArrayList<ModelOceny> listaOcen;
    ArrayList<Integer> listaOcen;
    float AverageMark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Bundle bundle = getIntent().getExtras();
        String sValue = bundle.getString(MainActivity.MARKS_KEY);

        if(savedInstanceState == null || !savedInstanceState.containsKey(KEY_ARRAYLIST)) {
            listaOcen = new ArrayList<Integer>();
            for(int i = 0; i < Integer.parseInt(sValue); i++) {
                int ocena = new Integer( 2);
                listaOcen.add(i,ocena);
            }
        }else{
            //listaOcen = savedInstanceState.getParcelableArrayList(KEY_ARRAYLIST);
            listaOcen = savedInstanceState.getIntegerArrayList(KEY_ARRAYLIST);
            Log.d("Wczytanie listy","Wczytanie listy");
        }


        recyclerView = findViewById(R.id.recyclerView);
        MyListAdapter MyAdapter = new MyListAdapter(SecondActivity.this, listaOcen);
        recyclerView.setAdapter(MyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SecondActivity.this));

        Button aveButton = findViewById(R.id.AvgButton);
        aveButton.setOnClickListener(
                view -> { AverageMark = computeAverage();
                    returnToMain();
                    } );


    }


    private float computeAverage() {
        float sum=0;
        for (int ocena : listaOcen)
            sum+=ocena;
        //Toast.makeText(this,"Average: "+ (sum/listaOcen.size()),Toast.LENGTH_LONG).show();
        float average = sum/listaOcen.size();
        return average;
    }

    public final static String RESULT_KEY = "com.example.lab2androidmr.result";
    private void returnToMain(){
        String AvgText = String.valueOf(AverageMark);
        Intent intent = new Intent();
        intent.putExtra(RESULT_KEY, AvgText);
        setResult(RESULT_OK, intent);
        finish();

    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        //outState.putParcelableArrayList(KEY_ARRAYLIST, listaOcen);
        outState.putIntegerArrayList(KEY_ARRAYLIST, listaOcen);
        super.onSaveInstanceState(outState);
    }


}


