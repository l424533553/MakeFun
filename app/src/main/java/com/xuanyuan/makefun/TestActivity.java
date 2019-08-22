package com.xuanyuan.makefun;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class TestActivity extends AppCompatActivity {
    private String TAG = "123456";
    private String Place = "TestActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        Log.i(TAG, Place + "=onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, Place + "=onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, Place + "=onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, Place + "=onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, Place + "=onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, Place + "=onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, Place + "=onDestroy");
    }

    /**
     *  使用使用情况
     *  @param outState  外层的状态
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, Place + "=onSaveInstanceState");
    }

    /**
     * 
     * @param savedInstanceState 外层的状态信息保存
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, Place + "=onRestoreInstanceState");
    }
}
