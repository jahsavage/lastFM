package com.discoid.testsavlastfm.io;

import com.discoid.testsavlastfm.io.model.Results;

import io.reactivex.Single;

public interface LastFMGateway {
    Single<Results> getArtist(String artistName);
    Single<Results> getAlbums(String album);
    Single<Results> getTracks(String track);
}