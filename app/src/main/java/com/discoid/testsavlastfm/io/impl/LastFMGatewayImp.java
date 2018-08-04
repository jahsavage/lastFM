package com.discoid.testsavlastfm.io.impl;

import com.discoid.testsavlastfm.io.LastFMGateway;
import com.discoid.testsavlastfm.io.model.Results;
import com.discoid.testsavlastfm.io.network.IResponseInterpreter;
import com.discoid.testsavlastfm.io.service.LastFMService;

import io.reactivex.Single;
import retrofit2.Response;

public class LastFMGatewayImp implements LastFMGateway {

    private LastFMService mLastFMService;
    private IResponseInterpreter mResponseInterpreter;

    public LastFMGatewayImp(LastFMService lastFMService, IResponseInterpreter responseInterpreter) {
        mResponseInterpreter = responseInterpreter;
        mLastFMService = lastFMService;
    }

    @Override
    public Single<Results> getArtist(String artistName) {
        return mLastFMService.getArtist(artistName)
                .doOnSuccess(mResponseInterpreter::filterErrors)
                .map(Response::body);
    }

    @Override
    public Single<Results> getAlbums(String album) {
        return mLastFMService.getAlbum(album)
                .doOnSuccess(mResponseInterpreter::filterErrors)
                .map(Response::body);
    }

    @Override
    public Single<Results> getTracks(String track) {
        return mLastFMService.getTrack(track)
                .doOnSuccess(mResponseInterpreter::filterErrors)
                .map(Response::body);
    }
}
