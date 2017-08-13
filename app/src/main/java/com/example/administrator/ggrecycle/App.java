package com.example.administrator.ggrecycle;

import android.app.Application;

import org.xutils.x;

/**
 * Created by Administrator on 2017/8/12.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
    }
}
