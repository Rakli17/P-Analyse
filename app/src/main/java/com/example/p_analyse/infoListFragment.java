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


public class infoListFragment extends ListFragment {
    OnInfoListSelectedListener mCallback;

    public interface OnInfoListSelectedListener{
        public void onInfoSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;
        if( Locale.getDefault().toString().startsWith("s")) {
            setListAdapter(new ArrayAdapter<String>(getActivity(), layout, pInfo.infoHeadlinesSE));
            System.out.println("Svensk");
        }
        else {
            setListAdapter(new ArrayAdapter<String>(getActivity(), layout, pInfo.infoHeadlinesDK));
            System.out.println(Locale.getDefault().toString());
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if(getFragmentManager().findFragmentById(R.id.info_fragment) != null){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mCallback = (OnInfoListSelectedListener) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement OnInfoListSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l,View v, int position, long id) {
        mCallback.onInfoSelected(position);

        getListView().setItemChecked(position,true);
    }




}
