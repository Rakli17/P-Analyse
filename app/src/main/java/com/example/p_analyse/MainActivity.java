package com.example.p_analyse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.ListFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.opencv.android.OpenCVLoader;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (OpenCVLoader.initDebug())
        {
            Toast.makeText(getApplicationContext(),"openCV loaded",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"openCV CRASHHHHHHHHH",Toast.LENGTH_SHORT).show();
        }

        ((Button) findViewById(R.id.infomationButton)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                directToInfoActivity();
            }
        });

        ((Button) findViewById(R.id.cameraButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directToCameraActivity();
            }
        });

    }

    private void directToInfoActivity() {
        Intent intent = new Intent(this, Info.class);
        startActivity(intent);
    }

    private void directToCameraActivity() {
        Intent intent = new Intent(this, Camera.class);
        startActivity(intent);
    }

    public void finishActivity() {
        finish();
    }
}
