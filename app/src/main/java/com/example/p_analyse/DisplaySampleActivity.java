package com.example.p_analyse;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DisplaySampleActivity extends AppCompatActivity {

    private static final String TAG = "DisplayActivity";
    TextView mName, mDate, mLeu, mPro, mBlo, mGlu, mNit;
    RecyclerViewAdapter Adapter;
    SampleClass data;
    // Cursor cursor;
    //@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_results);

        //Intent intent = this.getIntent();
        //Bundle bundle = intent.getExtras();
        //Type object = (Type) bundle.getSerializable("KEY");
        // Data data = (Data) bundle.getSerializable("KEY");

        if(getIntent().hasExtra("NameKey") && getIntent().hasExtra("DateKey")
                && getIntent().hasExtra("leuKey")
                && getIntent().hasExtra("proKey")
                && getIntent().hasExtra("bloKey")
                && getIntent().hasExtra("gluKey")
                && getIntent().hasExtra("nitKey")
        ){
            String name = getIntent().getStringExtra("nameKey");
            String date = getIntent().getStringExtra("dateKey");
            String leu = getIntent().getStringExtra("leuKey");
            String pro = getIntent().getStringExtra("proKey");
            String blo = getIntent().getStringExtra("bloKey");
            String glu = getIntent().getStringExtra("gluKey");
            String nit = getIntent().getStringExtra("nitKey");

            //if(getIntent().hasExtra("KEY")){
            Log.d(TAG, "Intent found");

            mName = findViewById(R.id.displayName);
            mDate = findViewById(R.id.displayDate);
            mLeu = findViewById(R.id.leu_result);
            mPro = findViewById(R.id.pro_result);
            mBlo = findViewById(R.id.blo_result);
            mGlu = findViewById(R.id.glu_result);
            mNit = findViewById(R.id.nit_result);

            mName.setText(name);
            mDate.setText(date);
            mLeu.setText(leu);
            mPro.setText(pro);
            mBlo.setText(blo);
            mGlu.setText(glu);
            mNit.setText(nit);

        }
    }
}