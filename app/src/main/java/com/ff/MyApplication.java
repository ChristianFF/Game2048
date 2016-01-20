package com.ff;

import android.app.Application;

import com.ff.util.SharedPreferencesUtils;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesUtils.init(this);
    }
}
