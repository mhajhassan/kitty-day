package com.nalovma.kittyday.pages.main;

import android.os.Bundle;

import com.nalovma.kittyday.R;
import com.nalovma.kittyday.base.BaseActivity;
import com.nalovma.kittyday.pages.breeds.BreedsFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.screenContainer, new BreedsFragment()).commit();

    }
}

