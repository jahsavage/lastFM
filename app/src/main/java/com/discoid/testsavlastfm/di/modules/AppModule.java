package com.discoid.testsavlastfm.di.modules;

import android.app.Application;
import android.content.Context;

import com.discoid.testsavlastfm.di.IScheduler;
import com.discoid.testsavlastfm.di.LiveScheduler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context providesContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    IScheduler provideScheduler() {
        return new LiveScheduler();
    }

}
