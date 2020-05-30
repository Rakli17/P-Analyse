package com.example.p_analyse;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.Timer;

import static org.opencv.imgproc.Imgproc.HoughCircles;
import static org.opencv.imgproc.Imgproc.cvtColor;

public class  Camera extends AppCompatActivity {

    ImageView imageView;
   // int numberOfPicture = getIntent().getIntExtra("countKey",0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imageView = findViewById(R.id.pictureView);


        ((Button) findViewById(R.id.btnCamera)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");


        finishActivity(bitmap);
    }

    private void directToTimerActivity() {
        Intent intent = new Intent(this, timer.class);
      //  intent.putExtra("numberOfPictureKey",numberOfPicture);
        startActivity(intent);
    }

    private void finishActivity(Bitmap b) {
        Intent intent = new Intent();
        intent.putExtra("PictureKey",b);
        setResult(RESULT_OK,intent);
        finish();
    }


}
