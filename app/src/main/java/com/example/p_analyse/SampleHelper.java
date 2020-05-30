package com.example.p_analyse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

class SampleHelper {

    SampleHelperHelper mSampleHelper;

    private static final String DATABASE_NAME = "USERINFO.DB";
    private static final int DATABASE_VERSIONS = 1;

    // opretter ny tabel
    public SampleHelper(Context context) {
        //   mDatabaseHelper = new DatabaseHelper(context);
        mSampleHelper = SampleHelperHelper.newInstance(context);
        //super(context, addDataClass.NewUserInfo.TABLE_NAME, null, DATABASE_VERSIONS);
        Log.e("DATABASE OPERATIONS", "Database created / opened..");

    }
    //tilføjer samples
    public boolean addSample(SampleClass p)
    {
        SQLiteDatabase db = mSampleHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(addSampleClass.NewSampleInfo.NAME, p.getName());
        contentValues.put(addSampleClass.NewSampleInfo.DATE, p.getDate());
        contentValues.put(addSampleClass.NewSampleInfo.LEU, p.getLeu());
        contentValues.put(addSampleClass.NewSampleInfo.PRO, p.getPro());
        contentValues.put(addSampleClass.NewSampleInfo.BLO, p.getBlo());
        contentValues.put(addSampleClass.NewSampleInfo.GLU, p.getGlu());
        contentValues.put(addSampleClass.NewSampleInfo.NIT, p.getNit());

        long result = db.insert(addSampleClass.NewSampleInfo.TABLE_NAME, null, contentValues);
        Log.e("DATABASE OPERATIONS", "One row inserted");

        if(result > 0) {
            return true;
        }
        return false;
    }

    //slet samples
    public void deleteSample(SampleClass p)
    {
        //henter nuværende database
        SQLiteDatabase db = mSampleHelper.getWritableDatabase();

        String query = "DELETE FROM " + addSampleClass.NewSampleInfo.TABLE_NAME + " WHERE "
                + addSampleClass.NewSampleInfo.NAME + " = '" + p.getName() + "'" +
                " AND " + addSampleClass.NewSampleInfo.DATE + " = '" + p.getDate() + "'" +
                " AND " + addSampleClass.NewSampleInfo.LEU + " = '" +  p.getLeu() + "'" +
                " AND " + addSampleClass.NewSampleInfo.PRO + " = '" + p.getPro() + "'" +
                " AND " + addSampleClass.NewSampleInfo.BLO + " = '" + p.getBlo() + "'" +
                " AND " + addSampleClass.NewSampleInfo.GLU + " = '" + p.getGlu() + "'" +
                " AND " + addSampleClass.NewSampleInfo.NIT + " = '" + p.getNit() +
                "'";
        Log.d("DatabaseHelper", "deleteName: query " + query);
        Log.d("DatabaseHelper", "deleteName: Deleting " + p.getName() + " From database " );
        db.execSQL(query);
    }

    //Bruges til at hentes databasen

    public ArrayList<SampleClass> getAllInfo()
    {
        ArrayList<SampleClass> dataList = new ArrayList<>();
        String SQL = "SELECT * FROM " + addSampleClass.NewSampleInfo.TABLE_NAME;/*+ " ORDER BY " +
                addDataClass.NewUserInfo.COLUMN_TIMESTAMP + " DESC"*/
        SQLiteDatabase db = mSampleHelper.getWritableDatabase();
        Log.d("DatabaseHelper", "deleteName: query " + db);
        Cursor cursor = db.rawQuery(SQL, null);
        if(cursor.moveToFirst()){
            do{
                SampleClass data = new SampleClass();
                data.setName(cursor.getString(0));
                data.setDate(cursor.getString(1));
                data.setLeu(cursor.getInt(2));
                data.setPro(cursor.getInt(3));
                data.setBlo(cursor.getInt(4));
                data.setGlu(cursor.getInt(5));
                data.setNit(cursor.getInt(6));
                dataList.add(data);
            }while (cursor.moveToNext());
        }
        return dataList;
    }
}

