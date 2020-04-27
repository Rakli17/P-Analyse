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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        textViewInfo = (TextView) inflater.inflate(R.layout.fragment_info, container, false);

        return textViewInfo;
    }

    @Override
    public void onStart() {
        super.onStart();

        Bundle args = getArguments();
        if (args != null) {
            updateInfoListView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            updateInfoListView(mCurrentPosition);
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

    public void updateInfoListView(int position) {
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
