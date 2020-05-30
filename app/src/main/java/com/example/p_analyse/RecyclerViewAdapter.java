package com.example.p_analyse;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";
    // Cursor mCursor;
    ArrayList<SampleClass> mDataList = new ArrayList<>();
    private ArrayList<String> nameList = new ArrayList<>();
    private ArrayList<String> dateList = new ArrayList<>();
    private ArrayList<String> leuList = new ArrayList<>();
    private ArrayList<String> proList = new ArrayList<>();
    private ArrayList<String> bloList = new ArrayList<>();
    private ArrayList<String> gluList = new ArrayList<>();
    private ArrayList<String> nitList = new ArrayList<>();

    // Herunder står der hvad context er, taget fra hjemmesiden:  https://developer.android.com/reference/android/content/Context
    // Interface to global information about an application environment.
    // This is an abstract class whose implementation is provided by the Android system.
    // It allows access to application-specific resources and classes,
    // as well as up-calls for application-level operations such as launching activities,
    // broadcasting and receiving intents, etc.
    private Context mContext;
    SampleClass data;

    //bliver kaldt fra viewSampleClass
    public RecyclerViewAdapter(Context context, ArrayList<SampleClass> dataList) {
        mDataList = dataList;
        mContext = context;
    }

    //@NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Her sættes instanser af layout_listitem, ind i recyclerviewet.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: Called ");
        //holder bliver sat, holder er de værdier der skal indsættes i recyclerviewet, position er den position de har i databasen
        data = mDataList.get(position);
        holder.tvName.setText(data.getName());
        holder.tvSampleDate.setText(data.getDate());

        //For at sende data videre til DisplaySampleActivity lægges
        // de over i arraylists der tager simple datatyper, da vi ikke kunne sende objekter.
        // Objektet ville have være en sampleClass.
        nameList.add(data.getName());
        dateList.add(data.getDate());
        leuList.add(String.valueOf(data.getLeu()));
        proList.add(String.valueOf(data.getPro()));
        bloList.add(String.valueOf(data.getBlo()));
        gluList.add(String.valueOf(data.getGlu()));
        nitList.add(String.valueOf(data.getNit()));

        holder.itemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onBindViewHolder: Called " + mDataList.get(position));
                //sætter view til displaysampleactivity
                Intent intent = new Intent(mContext, DisplaySampleActivity.class);
                //sender data over til displaySampleActivty
                intent.putExtra("nameKey", nameList.get(position));
                intent.putExtra("dateKey", dateList.get(position));
                intent.putExtra("leuKey", leuList.get(position));
                intent.putExtra("proKey", proList.get(position));
                intent.putExtra("bloKey", bloList.get(position));
                intent.putExtra("gluKey", gluList.get(position));
                intent.putExtra("nitKey", nitList.get(position));
                mContext.startActivity(intent);

            }
        });
    }

    //includeres idet vi extender recycleview
    @Override
    public int getItemCount() {
        return mDataList.size();
    }
    //opretter en viewholder, som er vores Layout_listitem.
    // Det er denne der bliver lagt ind i recyclerviewet længere oppe i koden i denne klasse.
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvSampleDate;
        ConstraintLayout itemList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvSampleDate = itemView.findViewById(R.id.tvSampleDate);
            itemList = itemView.findViewById(R.id.layout_listItem);
        }
    }
}
