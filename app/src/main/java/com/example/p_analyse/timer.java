package com.example.p_analyse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.Locale;

public class timer extends AppCompatActivity {

    private static final long Start_time = 6000;

    private TextView mTextViewTimer;
    private CountDownTimer mContdownTimer;
    private Boolean mTimerIsRunning;
    private long mTimeLeft = Start_time;

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
                directToCameraActivity();

            }
        }.start();
        mTimerIsRunning = true;

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
        startActivity(intent);
    }

}
