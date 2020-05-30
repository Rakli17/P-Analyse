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
    // sætter timer længden
    private static final long Start_time = 10000;

    private TextView mTextViewTimer;
    private CountDownTimer mContdownTimer;
    private Boolean mTimerIsRunning;
    private long mTimeLeft = Start_time;
    private int mCount = 0;


    RandomSample randomSample;

    // til at analysere bitmap
    Bitmap analyseBit;

    //create Thread
    Thread analyseThread;
    // to track Thread
    volatile boolean running = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        mTextViewTimer = findViewById(R.id.tvTimer);
        updateCountDownText();
        startTimer();

    }

    // starter timer
    private void startTimer() {
        mContdownTimer = new CountDownTimer(mTimeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeft = millisUntilFinished;
                updateCountDownText();
            }
            // når timeren er færdig kigger den efter hvormange gange den har kørt
            // når der er taget 3 billeder kalder den den metoden direct(randomeSample)
            // ellers kalder den camera activity.
            @Override
            public void onFinish() {
                mTimerIsRunning = false;
                if (mCount == 2 ) {
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


    // når den kommer tilbage fra camera resetter den timeren og starter forfra
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

//som metoden siger updatere den view med text så den tæller ned
    private void updateCountDownText(){
        int minutes = (int) (mTimeLeft / 1000) / 60;
        int seconds = (int) (mTimeLeft / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

        mTextViewTimer.setText(timeLeftFormatted);
    }

    // diregere til camera activity or caller for et resultat
    private void directToCameraActivity() {
        Intent intent = new Intent(this, Camera.class);
        startActivityForResult(intent,1);
    }


// når der er kommet et billede tilbage laver vi en thread som så kalder vores randome sample til vores resultat.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 ){
            if (resultCode == RESULT_OK){
                // for at vise at vi har vores data fra cameraet og det var her vi skulle have haft billede processering.
                Intent intent = data;
                Bitmap bitmap = (Bitmap) intent.getParcelableExtra("PictureKey");
                analyseBit = bitmap;
                // laver en thread hvor der skulle have været billede processering men nu bliver der genereret nogle random værdier
                // disse gives til randomeSample
                analyseThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(running){
                            if(analyseBit != null){
                                randomSample = PictureSample(analyseBit);
                                running = false;
                                 }}

                    }
                });
               analyseThread.start();

            }
        }
    }


    // metode til at komme til result activity som tager vores sample
    private void directToResultActivity(RandomSample r) {
        // vi sørger for at tråden bliver færdig først inden vi kan gå videre
        try {
            analyseThread.join();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        // opsætter vores intent og giver den de data som skal bruges i result viewet
        Intent intent = new Intent(getApplicationContext(), result.class);
        intent.putExtra("nameKey", r.createName());
        intent.putExtra("dateKey", r.createDate());
        intent.putExtra("leuKey", String.valueOf(r.createLeu()));
        intent.putExtra("proKey", String.valueOf(r.createPro()));
        intent.putExtra("bloKey", String.valueOf(r.createBlo()));
        intent.putExtra("gluKey", String.valueOf(r.createGlu()));
        intent.putExtra("nitKey", String.valueOf(r.createNit()));
        startActivity(intent);
    }

// metoden hvor der skulle have prosseceret et billede til at give vores værdier.
    private RandomSample PictureSample(Bitmap bit){
        analyseBit = bit;
        RandomSample r = new RandomSample();
            return r;
    }

// sørger for at vores Threads er i sync
    @Override
    protected void onDestroy() {
        running = false;

        super.onDestroy();
        try {
            analyseThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }


}
