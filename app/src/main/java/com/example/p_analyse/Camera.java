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

public class Camera extends AppCompatActivity {

    ImageView imageView;
    int numberOfPicture = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imageView = findViewById(R.id.pictureView);


        ((Button) findViewById(R.id.btnCamera)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        Mat mat = (Mat) data.getExtras().get("data");
        Bitmap bit32 = bitmap.copy(Bitmap.Config.ARGB_8888,true);
        //Mat mat = new Mat();
        Mat gray = new Mat();
//        Mat circles = new Mat();
//        Utils.bitmapToMat(bit32,mat);
        Imgproc.cvtColor(mat,gray,Imgproc.COLOR_BGR2GRAY);

        Utils.matToBitmap(gray,bit32);

        //  imageView.setImageBitmap(bit32);
        numberOfPicture = numberOfPicture + 1;
        if (numberOfPicture == 3) {
            directToResultActivity();
            numberOfPicture = 0;
        }
        else {
            directToTimerActivity();
        }

    }

    private void directToTimerActivity() {
        Intent intent = new Intent(this, timer.class);
        startActivity(intent);
    }

    private void directToResultActivity() {
        Intent intent = new Intent(this, result.class);
        startActivity(intent);
    }

}
