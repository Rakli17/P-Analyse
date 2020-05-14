package com.example.p_analyse;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;

import static org.opencv.imgproc.Imgproc.HoughCircles;
import static org.opencv.imgproc.Imgproc.cvtColor;

public class Camera extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2
{

//    ImageView imageView;
//    Thread imgThread;
//    volatile boolean running = true;

    private JavaCameraView mOpencvCameraView;
    private final static int REQUEST_CODE = 123;
    private Button mbutton;
    private Mat mrgba;
    private Mat mByte;
    private Mat imGray, imgCandy;
    CameraBridgeViewBase.CvCameraViewFrame inputFrame;

    BaseLoaderCallback mBaseloaderCallback = new BaseLoaderCallback() {
            @Override
            public void onManagerConnected(int status) {

                switch (status)
                {
                    case BaseLoaderCallback.SUCCESS: {
                        mOpencvCameraView.enableView();
                        break;
                    }default:{
                    super.onManagerConnected(status);
                    break;
                }
                }
            }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_camera);
        mbutton = (Button) findViewById(R.id.btnCamera);
        mOpencvCameraView = (JavaCameraView)findViewById(R.id.mycamera);
        mOpencvCameraView.setCvCameraViewListener(this);
        mOpencvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpencvCameraView.setCvCameraViewListener(this);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



//          imageView = findViewById(R.id.pictureView);
//
//
//        ((Button) findViewById(R.id.btnCamera)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, 0);
//            }
//        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        final Intent intent = data;
//        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//
//        Mat mat = new Mat();
//        System.out.println("HER");
//        Utils.bitmapToMat(bitmap,mat);
//
//          Bitmap bit32 = bitmap.copy(Bitmap.Config.ARGB_8888,true);
//
////        //Mat mat = new Mat();
//        Mat gray = new Mat();
////        Mat circles = new Mat();
////        Utils.bitmapToMat(bit32,mat);
//        Imgproc.cvtColor(mat,gray,Imgproc.COLOR_BGR2GRAY);
//
//
//
//        Utils.matToBitmap(gray,bit32);

//        imgThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //Make sure the thread is still supposed to run.
//                while (running) {
//
//
//                        Bitmap bitmap1 = (Bitmap) intent.getExtras().get("data");
//                        Mat mat = (Mat)intent.getExtras().get("data");
//                        Mat gray = new Mat();
//                        Imgproc.cvtColor(mat,gray,Imgproc.COLOR_BGR2GRAY);
//                        Utils.matToBitmap(gray,bitmap1);
//                    System.out.println("HEJ");
//                        imageView.setImageBitmap(bitmap1);
//
//
//                    //Have thread sleep for 10 seconds (10.000 ms)
//                    try {
//                        Thread.sleep(10000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });


//        imageView.setImageBitmap(bitmap);

    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mrgba = new Mat(width,height, CvType.CV_8UC4);
        mByte = new Mat(width,height, CvType.CV_8UC4);
        imGray = new Mat(width,height, CvType.CV_8UC4);
        imgCandy = new Mat(width,height, CvType.CV_8UC4);
    }

    @Override
    public void onCameraViewStopped() {
        mrgba.release();
        mByte.release();
        imGray.release();
        imgCandy.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mrgba = inputFrame.rgba();
        Imgproc.cvtColor(mrgba,imGray,Imgproc.COLOR_BGR2GRAY);
//        Imgproc.adaptiveThreshold(imGray, mByte, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 35, 5);
        Core.flip(imGray,imGray,-1);
//        imGray.reshape(200,200);
        return imGray; // this is m Binary image
    }


    @Override
    protected void onPause() {
        super.onPause();
        if(mOpencvCameraView!= null)
        {
            mOpencvCameraView.disableView();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!OpenCVLoader.initDebug())
        {
            Toast.makeText(getApplicationContext(),"there is a problem with OpenCV",Toast.LENGTH_SHORT).show();
        }
        else
        {
            mBaseloaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mOpencvCameraView!= null)
        {
            mOpencvCameraView.disableView();
        }
    }
}
