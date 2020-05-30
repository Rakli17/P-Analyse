package com.example.p_analyse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/*
Note Before you run the application.
1. We have changed the timer to 10 sek, so you dont have to wait 2 min  before you go further.
2. do to the complexity of the current time we did not succeed with the image processing with opencv. instead we decided as we talked about
focusing on the android fetures instead. we have made a randome sample class that generate random numbers for the sample so you still get the feeling on how the app should have worked.
*******************************************************************************************************************************************
projeckt discription
*********************************************************************************************************************************************
Students involved with this project:
Simon Thomsen : sitho18@student.sdu.dk
Lasse Jørgensen : lajoe18@student.sdu.dk
Rasmus Klitgaard : rakli17@student.sdu.dk

About the App
We want to create an app, that makes it easier for people in the health sector, to analyze pee tests. These test tells the user something about their health on different parameters, such as the level of protein, blood, ketons and so on.
The workflow of the app
1.	The start page includes a menu from which it’s possible to choose between, earlier examples, look up information about the test or create a new example.
2.	If you choose to create a new example
a.	A guide shows how the process is started
b.	Next, a timer starts and counts down from 30 and the camera opens and the picture is processed. This is repeated 3 times.
c.	Next, the results are shown. The user can now choose to either save it under your id or go back to the start page
3.	If you choose earlier examples.
a.	The screen will show a recyclerview of the earlier results, to make it possible to scroll. Also, we want to make a search function that shows the results under the given id.
b.	 As data is shown it should be possible to delete results and go back.
4.	If you choose information
a.	This will show a page with general information about the product.
Which Android key requirements we are going to use:
Threading: For when a picture processed in the background.
Database: For showing earlier results.
Fragments: This will be used for showing information. Also the layout will adapt to the screensize.
Adapters: Used for showing data.

 */

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
        Intent intent = new Intent(this, introToNewTest.class);
        startActivity(intent);
    }

    public void finishActivity() {
        finish();
    }
}