package com.example.p_analyse;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class result extends AppCompatActivity {
    private static final String TAG = "DisplayActivity";
    TextView mName, mDate, mLeu, mPro, mBlo, mGlu, mNit;
    RecyclerViewAdapter Adapter;
    SampleClass data;
    SampleHelper mSamplehelper;
    EditText writeableName;
    String name, date, leu, pro, blo, glu, nit;
    int ileu, ipro, iblo, iglu, init;
    //@Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        writeableName = (EditText) findViewById(R.id.nameText);

        ((Button) findViewById(R.id.btn_back)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                directToMainActivity();
            }
        });

        //Intent intent = this.getIntent();
        //Bundle bundle = intent.getExtras();
        //Type object = (Type) bundle.getSerializable("KEY");
        // Data data = (Data) bundle.getSerializable("KEY");

      /*  if(getIntent().hasExtra("nameKey")
                && getIntent().hasExtra("dateKey")
                && getIntent().hasExtra("leuKey")
                && getIntent().hasExtra("proKey")
                && getIntent().hasExtra("bloKey")
                && getIntent().hasExtra("gluKey")
                && getIntent().hasExtra("nitKey")
        ) {*/
           // data = getIntent().getExtras().getParcelable("data");
            //System.out.println("data   " + data.getName());

            //final String name = getIntent().getStringExtra("nameKey");
             name = writeableName.getText().toString();
        System.out.println();
             date = getIntent().getStringExtra("dateKey");
             leu = getIntent().getStringExtra("leuKey");
             pro = getIntent().getStringExtra("proKey");
             blo = getIntent().getStringExtra("bloKey");
             glu = getIntent().getStringExtra("gluKey");
             nit = getIntent().getStringExtra("nitKey");

            //if(getIntent().hasExtra("KEY")){
            Log.d(TAG, "Intent found");

        //mName = findViewById(R.id.displayName2);
        mDate = findViewById(R.id.displayDate2);
        mLeu = findViewById(R.id.leu_result2);
        mPro = findViewById(R.id.pro_result2);
        mBlo = findViewById(R.id.blo_result2);
        mGlu = findViewById(R.id.glu_result2);
        mNit = findViewById(R.id.nit_result2);


        //mName.setText(name);
        mDate.setText(date);
        mLeu.setText(leu);
        mPro.setText(pro);
        mBlo.setText(blo);
        mGlu.setText(glu);
        mNit.setText(nit);

        ileu = Integer.parseInt(leu);
        ipro = Integer.parseInt(pro);
        iblo = Integer.parseInt(blo);
        iglu = Integer.parseInt(glu);
        init = Integer.parseInt(nit);


        mSamplehelper = new SampleHelper(this);
        ((Button) findViewById(R.id.btn_save)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println(writeableName);
                data = new SampleClass(writeableName.getText().toString(), date, ileu, ipro, iblo, iglu, init);
                System.out.println("Name :  " + data.getName());
                if(mSamplehelper.addSample(data)) {
                    toastMessage("New sample created");
                    directToMainActivity();
                }else{
                    toastMessage("Could not create new sample");
                }
            }
        });

    }
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }
    private void directToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
