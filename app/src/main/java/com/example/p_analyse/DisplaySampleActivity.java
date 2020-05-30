package com.example.p_analyse;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DisplaySampleActivity extends AppCompatActivity {

    private static final String TAG = "DisplayActivity";
    TextView mName, mDate, mLeu, mPro, mBlo, mGlu, mNit;
    String name, date, leu, pro, blo, glu, nit;
    RecyclerViewAdapter Adapter;
    SampleClass data;

    // Cursor cursor;
    //@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_results);
        final SampleHelper deleteSample = new SampleHelper(this);


        //tjekker om data er blevet sendt fra recyclerViewAdapter
        if (getIntent().hasExtra("nameKey")
                && getIntent().hasExtra("dateKey")
                && getIntent().hasExtra("leuKey")
                && getIntent().hasExtra("proKey")
                && getIntent().hasExtra("bloKey")
                && getIntent().hasExtra("gluKey")
                && getIntent().hasExtra("nitKey")
        ) {
            //henter denne data fra recyclerViewAdapter
            name = getIntent().getStringExtra("nameKey");
            date = getIntent().getStringExtra("dateKey");
            leu = getIntent().getStringExtra("leuKey");
            pro = getIntent().getStringExtra("proKey");
            blo = getIntent().getStringExtra("bloKey");
            glu = getIntent().getStringExtra("gluKey");
            nit = getIntent().getStringExtra("nitKey");

            // finder tilhørende textviews.
            mName = findViewById(R.id.displayName);
            mDate = findViewById(R.id.displayDate);
            mLeu = findViewById(R.id.leu_result);
            mPro = findViewById(R.id.pro_result);
            mBlo = findViewById(R.id.blo_result);
            mGlu = findViewById(R.id.glu_result);
            mNit = findViewById(R.id.nit_result);

            //textviews får værdierne
            mName.setText(name);
            mDate.setText(date);
            mLeu.setText(leu);
            mPro.setText(pro);
            mBlo.setText(blo);
            mGlu.setText(glu);
            mNit.setText(nit);

            //sletter sample
            ((Button) findViewById(R.id.deleteBtn)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // sampleClass skal bruges nogle af værdierne som int
                    int ileu = Integer.parseInt(leu);
                    int ipro = Integer.parseInt(pro);
                    int iblo = Integer.parseInt(blo);
                    int iglu = Integer.parseInt(glu);
                    int init = Integer.parseInt(nit);
                    SampleClass s = new SampleClass(name, date, ileu, ipro, iblo, iglu, init);

                    deleteSample.deleteSample(s);
                    toastMessage("Sample deleted");
                    directToDisplay();

                }
            });
        }
    }
    private void toastMessage (String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
    private void directToDisplay() {
        Intent intent = new Intent(this, ViewSampleClass.class);
        startActivity(intent);
    }
}
