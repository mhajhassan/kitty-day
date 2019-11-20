package com.nalovma.kittyday.di.component;

import android.app.Application;

import com.nalovma.kittyday.base.BaseApplication;
import com.nalovma.kittyday.di.module.ActivityBindingModule;
import com.nalovma.kittyday.di.module.ApplicationModule;
import com.nalovma.kittyday.di.module.ContextModule;
import com.nalovma.kittyday.di.module.DbModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Singleton
@Component(modules = {ContextModule.class, ApplicationModule.class, AndroidSupportInjectionModule.class, ActivityBindingModule.class, DbModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        Builder dbModule(DbModule dbModule);
        ApplicationComponent build();
    }
}