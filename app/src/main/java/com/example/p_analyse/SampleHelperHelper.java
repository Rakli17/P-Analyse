package com.example.p_analyse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SampleHelperHelper extends SQLiteOpenHelper {

    private static SampleHelperHelper dbOpenHelper;

    private static final String CREATE_QUERY =
            "CREATE TABLE "+ addSampleClass.NewSampleInfo.TABLE_NAME+"("+
                    addSampleClass.NewSampleInfo.NAME+" TEXT,"+
                    addSampleClass.NewSampleInfo.DATE+" TEXT,"+
                    addSampleClass.NewSampleInfo.LEU+" INTEGER, "+
                    addSampleClass.NewSampleInfo.PRO+" INTEGER, "+
                    addSampleClass.NewSampleInfo.BLO+" INTEGER, "+
                    addSampleClass.NewSampleInfo.GLU+" INTEGER, "+
                    addSampleClass.NewSampleInfo.NIT+" INTEGER);";

    public static SampleHelperHelper newInstance(Context context){
        if (dbOpenHelper == null){
            dbOpenHelper = new SampleHelperHelper(context);
        }
        return dbOpenHelper;
    }

    private SampleHelperHelper(Context context) {
        super(context, addSampleClass.NewSampleInfo.TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SampleHelperHelper.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
