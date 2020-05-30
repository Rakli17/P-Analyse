package com.example.p_analyse;


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
        dataList = mSamplehelper.getAllInfo();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, dataList);

        rv.setAdapter(adapter);

        ((Button) findViewById(R.id.createNewSamplebtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RandomSample r = new RandomSample();
                SampleClass data = new SampleClass(r.createName(), r.createDate(), r.createLeu(), r.createPro(), r.createBlo(),r.createGlu(), r.createNit());

                if(mSamplehelper.addSample(data)){
                    toastMessage("New sample created");
                    mSamplehelper.updatePerson(data);
                }else{
                    toastMessage("Could not create new sample");
                }

            }
        });

    }
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
}
