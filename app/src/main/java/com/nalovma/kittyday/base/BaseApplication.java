package com.nalovma.kittyday.base;

import com.nalovma.kittyday.di.component.ApplicationComponent;
import com.nalovma.kittyday.di.component.DaggerApplicationComponent;
import com.nalovma.kittyday.di.module.DbModule;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;


public class BaseApplication extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent component = DaggerApplicationComponent.builder().application(this).dbModule(new DbModule()).build();
        component.inject(this);

        return component;
    }
}
