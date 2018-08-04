package com.discoid.testsavlastfm

import android.app.Activity
import android.app.Application
import android.support.v7.app.AppCompatDelegate
import com.discoid.testsavlastfm.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class LastFMApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        // Initialise debugger
        Timber.plant(Timber.DebugTree())

        DaggerAppComponent.builder()
                .create(this)
                .build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }
}
