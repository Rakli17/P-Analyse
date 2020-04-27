package com.example.p_analyse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentTransaction;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

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
    public void onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.infomation);
        if (fragment != null) {
            android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.remove(fragment);
            transaction.commit();
        } else {
            super.onBackPressed();
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
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

}
