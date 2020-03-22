package com.example.p_analyse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.infomationButton)).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                directToInfoActivity();
            }
        });
    }

    private void directToInfoActivity() {
        Intent intent = new Intent(this,Info.class);
        startActivity(intent);
    }

    public void finishActivity(){finish();}
}
