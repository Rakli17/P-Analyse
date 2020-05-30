package com.example.p_analyse;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class SampleClass {
    private int mLeu, mPro, mBlo, mGlu, mNit;

    String mDate = "dd-mm-yy";
    String mName;
    public SampleClass(){}

    public SampleClass(String name, String date, int leu, int pro, int blo, int glu, int nit){
        this.mName = name;
        this.mDate = date;
        this.mLeu = leu;
        this.mPro = pro;
        this.mBlo = blo;
        this.mGlu = glu;
        this.mNit = nit;
    }
    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String mDate) {
        this.mDate = mDate;
    }

    public int getLeu() {
        return mLeu;
    }

    public void setLeu(int mLeu) {
        this.mLeu = mLeu;
    }

    public int getPro() {
        return mPro;
    }

    public void setPro(int mPro) {
        this.mPro = mPro;
    }

    public int getBlo() {
        return mBlo;
    }

    public void setBlo(int mBlo) {
        this.mBlo = mBlo;
    }

    public int getGlu() {
        return mGlu;
    }

    public void setGlu(int mGlu) {
        this.mGlu = mGlu;
    }

    public int getNit() {
        return mNit;
    }

    public void setNit(int mNit) {
        this.mNit = mNit;
    }



}
