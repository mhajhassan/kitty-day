package com.nalovma.kittyday.di.module;

import com.nalovma.kittyday.pages.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {FragmentBindingModule.class})
    abstract MainActivity bindMainActivity();
}
