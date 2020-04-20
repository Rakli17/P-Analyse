package com.example.p_analyse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class Info extends AppCompatActivity implements infoListFragment.OnInfoListSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infomationslist);

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            infoListFragment firstFragment = new infoListFragment();

            firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
        }
    }


    @Override
    public void onInfoSelected(int position) {

        infomation infoListfrag = (infomation) getSupportFragmentManager().findFragmentById(R.id.info_fragment);

        if (infoListfrag != null) {
            infoListfrag.updateInfoListView(position);
        } else {

            infomation newInfoList = new infomation();
            Bundle args = new Bundle();
            args.putInt(infomation.ARG_POSITION, position);
            newInfoList.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            transaction.replace(R.id.fragment_container, newInfoList);

            transaction.commit();
        }
    }
}
