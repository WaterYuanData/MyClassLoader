package com.example.myclassloader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myclassloader.calculate.Devide;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void load(View view) {
        Log.i(TAG, "load: 类加载");
        String dexPath = "";
        String optimizedDirectory = "";
        String librarySearchPath = null;
        ClassLoader parent = getClassLoader();
        DexClassLoader dexClassLoader = new DexClassLoader(dexPath, optimizedDirectory, librarySearchPath, getClassLoader());
        BaseDexClassLoader baseDexClassLoader = new BaseDexClassLoader(dexPath, null, null, getClassLoader());
    }

    public void calculate(View view) {
        Log.i(TAG, "calculate: 计算");
        new Devide().testDevide();
    }
}
