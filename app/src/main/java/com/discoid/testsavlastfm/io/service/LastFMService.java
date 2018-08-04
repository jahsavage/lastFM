package com.discoid.testsavlastfm.io.service;

import com.discoid.testsavlastfm.io.model.Results;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LastFMService {

    @GET("?method=artist.search&format=json")
    public Single<Response<Results>> getArtist(@Query("artist") String artist);

    @GET("?method=artist.search&format=json")
    public Single<Response<Results>> getArtist(@Query("artist") String artist, @Query("page") Integer page,  @Query("limit") Integer limit);

    @GET("?method=album.search&format=json")
    public Single<Response<Results>> getAlbum(@Query("album") String album);

    @GET("?method=album.search&format=json")
    public Single<Response<Results>> getAlbum(@Query("album") String album, @Query("page") Integer page,  @Query("limit") Integer limit);

    @GET("?method=track.search&format=json")
    public Single<Response<Results>> getTrack(@Query("track") String album);

    @GET("?method=track.search&format=json")
    public Single<Response<Results>> getTrack(@Query("track") String album, @Query("page") Integer page,  @Query("limit") Integer limit);
}