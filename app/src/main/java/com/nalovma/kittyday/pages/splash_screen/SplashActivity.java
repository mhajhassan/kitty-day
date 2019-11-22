package com.nalovma.kittyday.pages.splash_screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.nalovma.kittyday.R;
import com.nalovma.kittyday.base.BaseActivity;
import com.nalovma.kittyday.pages.main.MainActivity;

public class SplashActivity extends BaseActivity {


    @Override
    protected int layoutRes() {
        return R.layout.splash_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);

    }
}
