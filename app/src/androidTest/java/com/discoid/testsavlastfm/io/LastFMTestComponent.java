package com.discoid.testsavlastfm.io;

import com.discoid.testsavlastfm.di.modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class})
public interface LastFMTestComponent {
    void inject(LastFMJTest lastFMGatewayTest);
    void inject(LastFMTest lastFMGatewayTest);
}
