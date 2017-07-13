package cn.keiss.where2pee.util;

import android.app.Application;

/**
 * Created by hekai on 2017/7/13.
 */

public class ContextGetter extends Application {
    private static ContextGetter instance;

    public static ContextGetter getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        instance = this;
    }

}