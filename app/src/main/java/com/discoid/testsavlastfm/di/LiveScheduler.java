package com.discoid.testsavlastfm.di;


import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jahsavage on 07/08/2016.
 */
public class LiveScheduler implements IScheduler {

    @Override public Scheduler currentThread() {
        return Schedulers.single();
    }

    @Override public Scheduler computationThread() {
        return Schedulers.computation();
    }

    @Override public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Override public Scheduler backgroundThread() {
        return Schedulers.io();
    }
}
