package com.example.myclassloader;

import android.app.Application;
import android.util.Log;

import com.example.myclassloader.multidex.EnjoyFix;

import java.io.File;

public class App extends Application {
    private static final String TAG = "App";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");

        EnjoyFix.init(getApplicationContext());
        // TODO: 2019/7/24
//        EnjoyFix.installPatch(getApplicationContext(), new File("/_ae/fix.dex"));
    }
}
