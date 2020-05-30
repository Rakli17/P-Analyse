package com.example.p_analyse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class timer extends AppCompatActivity {

    private static final long Start_time = 2000;

    private TextView mTextViewTimer;
    private CountDownTimer mContdownTimer;
    private Boolean mTimerIsRunning;
    private long mTimeLeft = Start_time;
    private int mCount = 0;
    ArrayList<SampleClass> dataList;
    SampleHelper mSamplehelper;

    RandomSample randomSample;

    //create Thread
    Thread analyseThread;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        mTextViewTimer = findViewById(R.id.tvTimer);

        updateCountDownText();

        startTimer();

    }

    private void startTimer() {
        mContdownTimer = new CountDownTimer(mTimeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeft = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerIsRunning = false;
                if (mCount == 2 ) {
                    System.out.println("JEG ER HER");
                    directToResultActivity(randomSample);
                    mCount = 0;
                }
                else
                    mCount ++;
                directToCameraActivity();

            }
        }.start();
        mTimerIsRunning = true;

    }



    @Override
    protected void onRestart() {
        super.onRestart();
        resetTimer();
        startTimer();
    }

    private void resetTimer(){
        mTimeLeft = Start_time;
        updateCountDownText();
    }


    private void updateCountDownText(){
        int minutes = (int) (mTimeLeft / 1000) / 60;
        int seconds = (int) (mTimeLeft / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

        mTextViewTimer.setText(timeLeftFormatted);
    }

    private void directToCameraActivity() {
        Intent intent = new Intent(this, Camera.class);
        startActivityForResult(intent,1);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 ){
            if (resultCode == RESULT_OK){
                Intent intent = data;
                Bitmap bitmap = (Bitmap) intent.getParcelableExtra("PictureKey");
               randomSample = PictureSample();
            }
        }
    }



    private void directToResultActivity(RandomSample r) {


        Intent intent = new Intent(getApplicationContext(), result.class);
        System.out.println("START ACTIVITY " + r.createGlu());
        intent.putExtra("nameKey", r.createName());
        intent.putExtra("dateKey", r.createDate());
        intent.putExtra("leuKey", String.valueOf(r.createLeu()));
        intent.putExtra("proKey", String.valueOf(r.createPro()));
        intent.putExtra("bloKey", String.valueOf(r.createBlo()));
        intent.putExtra("gluKey", String.valueOf(r.createGlu()));
        intent.putExtra("nitKey", String.valueOf(r.createNit()));

        System.out.println("START ACTIVITY");
        startActivity(intent);
    }


    private RandomSample PictureSample(){
        RandomSample r = new RandomSample();
            return r;
    }
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }


}
