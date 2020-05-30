package com.example.p_analyse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class introToNewTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_to_new_test);
        ((Button) findViewById(R.id.btn_begin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directToTimerActivity();
            }
        });

    }



    private void directToTimerActivity() {
        Intent intent = new Intent(this, timer.class);
        startActivity(intent);
    }

}
