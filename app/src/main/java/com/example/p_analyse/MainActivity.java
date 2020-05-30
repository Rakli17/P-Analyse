package com.example.p_analyse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
public int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.infomationButton)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                directToInfoActivity();
            }
        });

        ((Button) findViewById(R.id.preSamBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directToCameraActivity();
            }
        });

        ((Button) findViewById(R.id.timerButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directToTimerActivity();
            }
        });

        ((Button) findViewById(R.id. preSamBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directToPreviousSampleActivity();
            }
        });
    }

    private void directToInfoActivity() {
        Intent intent = new Intent(this, Info.class);
        startActivity(intent);
    }
    private void directToPreviousSampleActivity() {
        Intent intent = new Intent(this, ViewSampleClass.class);
        startActivity(intent);
    }
    private void directToCameraActivity() {
        Intent intent = new Intent(this, Camera.class);
        startActivity(intent);
    }


    private void directToTimerActivity() {
        Intent intent = new Intent(this, timer.class);
        startActivity(intent);
    }

    public void finishActivity() {
        finish();
    }
}