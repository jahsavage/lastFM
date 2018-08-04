package com.discoid.testsavlastfm.di.modules;

import com.discoid.testsavlastfm.io.LastFMGateway;
import com.discoid.testsavlastfm.io.impl.LastFMGatewayImp;
import com.discoid.testsavlastfm.io.network.IResponseInterpreter;
import com.discoid.testsavlastfm.io.network.ResponseInterpreter;
import com.discoid.testsavlastfm.io.service.AddApiKeyInterceptor;
import com.discoid.testsavlastfm.io.service.LastFMService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

@Module
public class NetworkModule {

    final static String LAST_FM_BASE_URL = "http://ws.audioscrobbler.com/2.0/";
    final static String API_KEY = "c02d335442774120cef7b1bd74f5d354";

    public NetworkModule() {
    }

    @Provides
    @Singleton
    AddApiKeyInterceptor provideAddApiKeyInterceptor() {
        return new AddApiKeyInterceptor(API_KEY);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(AddApiKeyInterceptor addApiKeyInterceptor) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(addApiKeyInterceptor);
        httpClient.addInterceptor(logging);

        return httpClient.build();
    }


    @Provides
    @Singleton
    LastFMService provideLastFMService(OkHttpClient client) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LAST_FM_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit.create(LastFMService.class);
    }

    @Provides
    @Singleton
    IResponseInterpreter provideResponseInterpreter() {
        return new ResponseInterpreter();
    }

    @Provides
    @Singleton
    LastFMGateway provideLastFMGateway(LastFMService lastFMService, IResponseInterpreter responseInterpreter) {
        return new LastFMGatewayImp(lastFMService, responseInterpreter);
    }

}
