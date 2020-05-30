package com.example.p_analyse;

import android.app.Application;

public class GlobalClass extends Application {

    private int count = 0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
