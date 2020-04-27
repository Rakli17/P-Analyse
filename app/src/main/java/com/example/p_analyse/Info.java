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

        // kigger på om activity bruger layoutet med fragment containeren framelayout hvis dette er tilfældet måd er tilføjes den første fragment.
        if (findViewById(R.id.fragment_container) != null) {

            // dog hvis den bliver restored fra tidligere skal vi ikke gøre noget da vi ellers vil kunne komme til at at overlappe fragmenterne.
            if (savedInstanceState != null) {
                return;
            }

            //skaber in instans af fragment
            infoListFragment firstFragment = new infoListFragment();

            // hvis denne activity er started med special instructioner for eksemple ved en intent
            // gives intentens ekstra videre til fragmentet som et argument.
            firstFragment.setArguments(getIntent().getExtras());

            //tilføjer fragmentet til container framelayoutet
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();
        }
    }

    @Override
    public void onBackPressed() {
        //An instance of the android.app.FragmentManager class takes care of your app’s fragments.
        //The manager also helps you fiddle with your activity’s back stack.

        //returnere fragmentmanageren til at inteagere med fragmenter tilhørende med denne activitet.
        FragmentManager fragmentManager = getFragmentManager();
        // finder fragmentet
        Fragment fragment = fragmentManager.findFragmentById(R.id.infomation);
        if (fragment != null) {
            //FragmentManager which is used to create transactions for adding, removing or replacing fragments.
            android.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
            // bruges til at fjerne et fragment fra  back stacken
            transaction.remove(fragment);
            // comitter den valgte transaction 
            transaction.commit();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onInfoSelected(int position) {

        // brugeren har valgt en information fra infolistFragment
        // Capture the article fragment from the activity layout
        infomation infoListfrag = (infomation) getSupportFragmentManager().findFragmentById(R.id.info_fragment);

        // hvis information fragment er available er vi i two-pane layout altså tablet tilstand
        if (infoListfrag != null) {
            // kalder metode fra informations fragmentet til at updatere dets information.
            infoListfrag.updateInfoListView(position);
        // hvis devices er one-pane device skal der skiftes fragments.
        } else {
            // creates an fragment and give it an argumant for the selected information
            infomation newInfoList = new infomation();
            Bundle args = new Bundle();
            args.putInt(infomation.ARG_POSITION, position);
            newInfoList.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // replacere hvad der er i containeren viewet med denne fragment og tilføjer transationen i backstacken så brugeren kan navigere tilbage.
            // og tilsidst comitter vi vores transaction.
            transaction.replace(R.id.fragment_container, newInfoList);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

}
