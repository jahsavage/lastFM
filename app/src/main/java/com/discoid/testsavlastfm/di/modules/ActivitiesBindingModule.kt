package com.discoid.testsavlastfm.di.modules

import com.discoid.testsavlastfm.di.scopes.PerActivity
import com.discoid.testsavlastfm.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by mertsimsek on 25/05/2017.
 */
@Module
abstract class ActivitiesBindingModule {

    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun mainActivity(): MainActivity

}
