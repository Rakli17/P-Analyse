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

    private Context mContext;
    SampleClass data;

    public RecyclerViewAdapter(Context context, ArrayList<SampleClass> dataList) {
        mDataList = dataList;
        mContext = context;
    }

    //@NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: Called ");
        data = mDataList.get(position);
        holder.tvName.setText(data.getName());
        holder.tvSampleDate.setText(data.getDate());

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

                // lav array for hver data
                Intent intent = new Intent(mContext, DisplaySampleActivity.class);
                //intent.putExtra("myKey", mDataList.get(position));
                intent.putExtra("nameKey", nameList.get(position));
                intent.putExtra("dateKey", dateList.get(position));
                intent.putExtra("leuKey", leuList.get(position));
                intent.putExtra("proKey", proList.get(position));
                intent.putExtra("bloKey", bloList.get(position));
                intent.putExtra("gluKey", gluList.get(position));
                intent.putExtra("nitKey", nitList.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {

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
