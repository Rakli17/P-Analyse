package com.example.p_analyse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.ListFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


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

        ((Button) findViewById(R.id.cameraButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directToCameraActivity();
            }
        });

    }

    private void directToInfoActivity() {
        System.out.println("5+5 = " + 5 + 5);
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
