package com.nalovma.kittyday.di.module;

import com.nalovma.kittyday.pages.main.MainActivity;
import com.nalovma.kittyday.pages.main.MainFragmentBindingModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindMainActivity();
}
