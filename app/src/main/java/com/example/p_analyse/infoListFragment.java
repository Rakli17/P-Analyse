package com.example.p_analyse;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Locale;

// container activity skal implementere dette interface for at fragmenterne kan gi ve beskeder.
// derfor er denne klasse implementeret i Info.class
public class infoListFragment extends ListFragment {
    OnInfoListSelectedListener mCallback;

    // Kaldes af infoList fragmentet når en information er valgt
    public interface OnInfoListSelectedListener {
        public void onInfoSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        int layout = android.R.layout.simple_list_item_1;

        //igen skal der kigges på sproget for at fast sætte hvilket sprog device er sat op til igen er det fordi vi snakker om statiske variabler
        // default er opsat til dansk
        if (Locale.getDefault().toString().startsWith("s")) {
            setListAdapter(new ArrayAdapter<String>(getActivity(), layout, pInfo.infoHeadlinesSE));
            System.out.println("Svensk");
        }
        else if(Locale.getDefault().toString().startsWith("n")){
            setListAdapter(new ArrayAdapter<String>(getActivity(), layout, pInfo.infoHeadlinesNO));
            System.out.println("norsk");
        }
        else {
            setListAdapter(new ArrayAdapter<String>(getActivity(), layout, pInfo.infoHeadlinesDK));
            System.out.println(Locale.getDefault().toString());
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // When in two-pane layout, set the listview to highlight the selected list item
        // (We do this during onStart because at the point the listview is available.)
        if (getFragmentManager().findFragmentById(R.id.info_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //denne sikre at container activiteten her implementeret callback interfacet, hvis ikke bliver der kastet med en exception.
        try {
            mCallback = (OnInfoListSelectedListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnInfoListSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // da der på plads 9 i arrayet er et navnet Test Information: som ikke kan trykkes på da dette bare betyder at herfra og ned kommer der
        // mere specifik test information. derfor skere der ikke noget ved at trykke på denne i informationen.
        if(position == 9){}
        else if (position > 9) {
            position = position - 1;
            // notificere parent activityen omkring det valgte information
            mCallback.onInfoSelected(position);
            // sætter det valgte til at være highlighted når man er i tablet tilstand
            getListView().setItemChecked(position, true);
        } else {
            position = position;
            mCallback.onInfoSelected(position);

            getListView().setItemChecked(position, true);
        }

    }




}
