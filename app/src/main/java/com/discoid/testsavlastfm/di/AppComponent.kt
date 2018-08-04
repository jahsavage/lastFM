package com.discoid.testsavlastfm.di

import android.app.Application
import com.discoid.testsavlastfm.LastFMApplication
import com.discoid.testsavlastfm.di.modules.ActivitiesBindingModule
import com.discoid.testsavlastfm.di.modules.AppModule
import com.discoid.testsavlastfm.di.modules.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by jahsavage on 10/08/2016.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NetworkModule::class,
        ActivitiesBindingModule::class,
        AndroidSupportInjectionModule::class))
interface AppComponent : AndroidInjector<LastFMApplication> {

    override fun inject(app: LastFMApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application):Builder
        fun build(): AppComponent
    }
}
