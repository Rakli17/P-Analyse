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


    public SampleHelper(Context context) {
        //   mDatabaseHelper = new DatabaseHelper(context);
        mSampleHelper = SampleHelperHelper.newInstance(context);
        //super(context, addDataClass.NewUserInfo.TABLE_NAME, null, DATABASE_VERSIONS);
        Log.e("DATABASE OPERATIONS", "Database created / opened..");

    }

    /*public String selectName(String name){
        String str = "";
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+ addDataClass.NewUserInfo.USER_NAME + " FROM "
                + addDataClass.NewUserInfo.TABLE_NAME + " WHERE " + addDataClass.NewUserInfo.USER_NAME
                + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);

        if(data.moveToFirst()){
            str = data.getString(data.getColumnIndex("Content"));

        }
        return str;

    }*/
    public void selectDate(String date){

    }
    public void selectEmail(String email){

    }
    public boolean updatePerson(SampleClass p) {
        SQLiteDatabase db = mSampleHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(addSampleClass.NewSampleInfo.NAME, p.getName());
        contentValues.put(addSampleClass.NewSampleInfo.DATE, p.getDate());
        contentValues.put(addSampleClass.NewSampleInfo.LEU, p.getLeu());
        contentValues.put(addSampleClass.NewSampleInfo.PRO, p.getPro());
        contentValues.put(addSampleClass.NewSampleInfo.BLO, p.getBlo());
        contentValues.put(addSampleClass.NewSampleInfo.GLU, p.getGlu());
        contentValues.put(addSampleClass.NewSampleInfo.NIT, p.getNit());

        int result = db.update(
                addSampleClass.NewSampleInfo.TABLE_NAME,
                contentValues, "name = ?",
                new String[]{p.getName()}
        );

        if (result > 0) {
            return true;
        }
        return false;
    }
    /*public ArrayList<SampleClass> findAllPersons() {

        ArrayList<SampleClass> dataList = new ArrayList<>();

        SQLiteDatabase db = mSampleHelper.getReadableDatabase();
        Cursor cursor = db.query(
                addSampleClass.NewSampleInfo.TABLE_NAME,
                new String[]{
                        addSampleClass.NewSampleInfo.NAME,
                        addSampleClass.NewSampleInfo.DATE,
                        addSampleClass.NewSampleInfo.LEU,
                        addSampleClass.NewSampleInfo.PRO,
                        addSampleClass.NewSampleInfo.BLO,
                        addSampleClass.NewSampleInfo.GLU,
                        addSampleClass.NewSampleInfo.NIT,
                        },
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                SampleClass p = SampleClass.getData(cursor);
                if (p != null) {
                    dataList.add(p);
                }
                cursor.moveToNext();
            }
            return dataList;
        }
        return dataList;
        //return Collections.emptyList();
    }*/
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

    //slet data
    public void deleteName(String name, String date)
    {
        SQLiteDatabase db = mSampleHelper.getWritableDatabase();
        String query = "DELETE FROM " + addSampleClass.NewSampleInfo.TABLE_NAME + " WHERE "
                + addSampleClass.NewSampleInfo.NAME + " = '" + name + "'" +
                " AND " + addSampleClass.NewSampleInfo.DATE + " = '" + date + "'";

        Log.d("DatabaseHelper", "deleteName: query " + query);
        Log.d("DatabaseHelper", "deleteName: Deleting " + name + " From database " );
        db.execSQL(query);
    }

    //Get data
    public Cursor getData(){
        //retunerer alt data

        SQLiteDatabase db = mSampleHelper.getWritableDatabase();
        String query = "SELECT * FROM " + addSampleClass.NewSampleInfo.TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
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

