package com.example.p_analyse;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;


public class infomation extends Fragment {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;
    private TextView textViewInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
       // hvis en activity rekreaeres (fra f.eks. rotation af skærm)
        // restoreres den sidste information der var valgt af onSaveInstanceState()
        // dette benyttes primært ved tlf. med to skærme fragmenter.
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        // inflater layoutet med det valgte fragment.
        textViewInfo = (TextView) inflater.inflate(R.layout.fragment_info, container, false);
        //Billedresultat for android programming inflater
        //Layout inflater is a class that reads the xml appearance description and convert them into java based View objects.
        // LayoutInflater is a fundamental component in Android. You must use it all the time to turn xml files into view hierarchies
        //https://developer.android.com/reference/android/view/LayoutInflater.html
        //Instantiates a layout XML file into its corresponding View objects. It is never used directly. Instead, use Activity.getLayoutInflater() or Context#getSystemService
        // to retrieve a standard LayoutInflater instance that is already hooked up to the current context and correctly configured for the device you are running on.
        //To create a new LayoutInflater with an additional Factory for your own views, you can use cloneInContext(Context) to clone an existing ViewFactory, and then
        // call setFactory(LayoutInflater.Factory) on it to include your Factory.
        //For performance reasons, view inflation relies heavily on pre-processing of XML files that is done at build time. Therefore, it is not currently possible to
        // use LayoutInflater with an XmlPullParser over a plain XML file at runtime; it only works with an XmlPullParser returned from a compiled resource (R.something file.)
        //Note: This class is not thread-safe and a given instance should only be accessed by a single thread.
        return textViewInfo;
    }

    @Override
    public void onStart() {
        super.onStart();
        // under startup checkes der efter om der er argumenter givet videre til fragmenterne
        // layoutet er allerede blevet tilføjet til fragment på dette tidspunkt så derfor kan vi med sikkerhed klade denne methode.
        //
        //opsætter informations teksterne i fragmenterne
        Bundle args = getArguments();
        if (args != null) {
            // sætter informationen efter input argumentet
            updateInfoListView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // sætter informationen baseret på savedInstance status defineret under onCreateView()
            updateInfoListView(mCurrentPosition);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // gemmer den sidste information if fragmentet skal rekreeres
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

    public void updateInfoListView(int position) {
        // updatere informationen efter positionen og string arrayet fra pInfo.
        // der kigges efter om hvilken sprog setting tlf. er opsat til for at give den rigtige tekst til denne.
        // der kunne efter hvad jeg ved af ikke bruges resource files til dette da det kræves at string arrayet er statisk og det kan det ikke være hvis det ændres fra resource.
        if (Locale.getDefault().toString().startsWith("s")) {
            textViewInfo.setText(pInfo.informationSE[position]);
            System.out.println("SVENSK");
        } else if (Locale.getDefault().toString().startsWith("n")) {
            textViewInfo.setText(pInfo.informationNO[position]);
        } else {
            textViewInfo.setText(pInfo.informationDK[position]);
            System.out.println(Locale.getDefault().toString());
        }
        mCurrentPosition = position;
    }

}
