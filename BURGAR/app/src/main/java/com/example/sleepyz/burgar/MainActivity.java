package com.example.sleepyz.burgar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;


@TargetApi(21)
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}