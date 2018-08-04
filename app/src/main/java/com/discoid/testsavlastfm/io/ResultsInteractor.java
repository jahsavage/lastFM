package com.discoid.testsavlastfm.io;

import com.discoid.testsavlastfm.di.IScheduler;
import com.discoid.testsavlastfm.io.model.Results;
import com.discoid.testsavlastfm.io.network.IResponseInterpreter;
import com.discoid.testsavlastfm.view.SearchType;

import io.reactivex.Single;

public class ResultsInteractor implements IResultsInteractor {

    private LastFMGateway mLastFMGateway;
    private IScheduler mScheduler;
    private IResponseInterpreter mResponseInterpreter;

    public ResultsInteractor(LastFMGateway lastFMGateway, IScheduler scheduler, IResponseInterpreter responseInterpreter) {
        mLastFMGateway = lastFMGateway;
        mScheduler = scheduler;
        mResponseInterpreter = responseInterpreter;
    }

    @Override
    public Single<Results> getResults(String query, SearchType searchType) {
        return getApiCall(query, searchType)
                .observeOn(mScheduler.mainThread())
                .subscribeOn(mScheduler.backgroundThread());
    }

    private Single<Results> getApiCall(String query, SearchType searchType) {
        Single<Results> apiCall = null;

        switch(searchType) {
            case ARTIST:
                apiCall = mLastFMGateway.getArtist(query);
                break;
            case ALBUM:
                apiCall =  mLastFMGateway.getAlbums(query);
                break;
            case TRACK:
                apiCall = mLastFMGateway.getTracks(query);
                break;
        }

        return apiCall;
    }
}
