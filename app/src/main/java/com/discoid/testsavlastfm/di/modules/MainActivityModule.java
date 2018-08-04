package com.discoid.testsavlastfm.di.modules;

import com.discoid.testsavlastfm.di.IScheduler;
import com.discoid.testsavlastfm.di.scopes.PerActivity;
import com.discoid.testsavlastfm.io.IResultsInteractor;
import com.discoid.testsavlastfm.io.LastFMGateway;
import com.discoid.testsavlastfm.io.ResultsInteractor;
import com.discoid.testsavlastfm.io.network.IResponseInterpreter;
import com.discoid.testsavlastfm.presenter.ILastFMPresenterInputs;
import com.discoid.testsavlastfm.presenter.LastFMLastPresenterImpl;
import com.discoid.testsavlastfm.presenter.ResultsConverter;
import com.discoid.testsavlastfm.view.ResultAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {



    @Provides
    @PerActivity
    IResultsInteractor provideResultsInteractor(LastFMGateway lastFMGateway, IScheduler scheduler, IResponseInterpreter responseInterpreter) {
        return new ResultsInteractor(lastFMGateway, scheduler, responseInterpreter);
    }

    @Provides
    @PerActivity
    ILastFMPresenterInputs providePresenter(IResultsInteractor resultsInteractor) {
        return new LastFMLastPresenterImpl(resultsInteractor, new ResultsConverter());
    }

    @Provides
    @PerActivity
    ResultAdapter provideResultAdapter() {
        return new ResultAdapter();
    }
}
