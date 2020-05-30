package com.example.p_analyse;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewSampleClass extends AppCompatActivity {

    private static final String TAG = "NewContactActivity";
    RecyclerView rv;
    ArrayList<SampleClass> dataList;
    SampleHelper mSamplehelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_list);
        Log.d(TAG, "onCreate started");
        mSamplehelper = new SampleHelper(this);

        Log.d(TAG, "onCreate: displayactivity ");

        rv = (RecyclerView) findViewById(R.id.recycleView);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        dataList = new ArrayList<>();
        //henter samples fra databsen
        dataList = mSamplehelper.getAllInfo();
        //sender data over i recyclerview
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, dataList);

        rv.setAdapter(adapter);

        //backbutton
        ((Button) findViewById(R.id.backToMain)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directToMainActivity();
            }
        });

    }
    private void directToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
