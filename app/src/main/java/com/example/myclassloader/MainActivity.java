package com.example.myclassloader;

import android.Manifest;
import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.myclassloader.calculate.Devide;
import com.example.myclassloader.utils.PermissonUtils;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] permissons = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        PermissonUtils.getInstance().checkAndRequestPermissions(this, permissons, 666);
    }

    public void load(View view) {
        Log.i(TAG, "load: 类加载");
        Log.i(TAG, "load: getClassLoader()=" + getClassLoader()); // dalvik.system.PathClassLoader[DexPathList[[zip file "/data/app/com.example.myclassloader-1/base.apk", zip file "/data/app/com.example.myclassloader-1/split_lib_dependencies_apk.apk", zip file "/data/app/com.example.myclassloader-1/split_lib_slice_0_apk.apk", zip file "/data/app/com.example.myclassloader-1/split_lib_slice_1_apk.apk", zip file "/data/app/com.example.myclassloader-1/split_lib_slice_2_apk.apk", zip file "/data/app/com.example.myclassloader-1/split_lib_slice_3_apk.apk", zip file "/data/app/com.example.myclassloader-1/split_lib_slice_4_apk.apk", zip file "/data/app/com.example.myclassloader-1/split_lib_slice_5_apk.apk", zip file "/data/app/com.example.myclassloader-1/split_lib_slice_6_apk.apk", zip file "/data/app/com.example.myclassloader-1/split_lib_slice_7_apk.apk", zip file "/data/app/com.example.myclassloader-1/split_lib_slice_8_apk.apk", zip file "/data/app/com.example.myclassloader-1/split_lib_slice_9_apk.apk"],nativeLibraryDirectories=[/data/app/com.example.myclassloader-1/lib/arm64, /system/lib64, /vendor/lib64, /system/vendor/lib64, /product/lib64]]]
        Log.i(TAG, "load: getClassLoader().getParent()=" + getClassLoader().getParent()); // java.lang.BootClassLoader@5900542
        Log.i(TAG, "load: Activity.class.getClassLoader()=" + Activity.class.getClassLoader()); // java.lang.BootClassLoader@5900542

        try {
            getClassLoader().loadClass("com.yuan.hotfix.andfix.ok.Calculator");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "load: getClassLoader 系统创建的PathClassLoader不能加载我们指定的dex");
        }

        Log.i(TAG, "load: getExternalStorageDirectory=" + Environment.getExternalStorageDirectory().getAbsolutePath()); // /storage/emulated/0
        Log.i(TAG, "load: getExternalStorageDirectory=" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS).getAbsolutePath()); // /storage/emulated/0
        Log.i(TAG, "load: getCodeCacheDir=" + getCodeCacheDir().getAbsolutePath()); // /data/user/0/com.example.myclassloader/code_cache
        String dexPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/_ae/fix.dex";
        // IOException: No original dex files found for dex location /storage/emulated/0/_ae/fix.dex
        // 权限申请
        DexClassLoader dexClassLoader = new DexClassLoader(dexPath, getCodeCacheDir().getAbsolutePath(), null, getClassLoader());
        try {
            dexClassLoader.loadClass("com.yuan.hotfix.andfix.ok.Calculator");
            Log.i(TAG, "load: 加载到了");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "load: dexClassLoader");
        }
        PathClassLoader PathClassLoader = new PathClassLoader(dexPath, null, getClassLoader());
        try {
            PathClassLoader.loadClass("com.yuan.hotfix.andfix.ok.Calculator");
            Log.i(TAG, "load: 我们自己创建的PathClassLoader可以加载我们指定的dex");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG, "load: PathClassLoader");
        }
    }

    public void calculate(View view) {
        Log.i(TAG, "calculate: 计算");
        new Devide().testDevide();
    }
}
