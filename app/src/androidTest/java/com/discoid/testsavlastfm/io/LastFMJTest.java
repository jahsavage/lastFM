package com.discoid.testsavlastfm.io;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.discoid.testsavlastfm.di.modules.NetworkModule;
import com.discoid.testsavlastfm.io.model.Results;
import com.discoid.testsavlastfm.io.model.Results_;

import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;

import io.reactivex.observers.TestObserver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LastFMJTest {

    private final int dataPageLimit = 30;

    @Inject
    LastFMGateway mLastFMGatway;

    @Before
    public void init() {
        LastFMTestComponent testAppComponent = DaggerLastFMTestComponent.builder()
                .networkModule(new NetworkModule())
                .build();
        testAppComponent.inject(this);
    }

    @Test
    public void  useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.discoid.testsavlastfm", appContext.getPackageName());
    }

    @Test
    public void  fetchNoArtists() {
        String artistName = "XXZZ454B52s";

        TestObserver<Results> testSubscriber = mLastFMGatway.getArtist(artistName).test();
        Results_ results = testSubscriber.values().get(0).getResults();
        assertEquals("0", results.getOpensearchTotalResults());
        assertEquals(0, results.getArtistmatches().getArtist().size());
    }

    @Test
    public void fetchArtists() {
        String artistName = "Sex Pistols";

        TestObserver<Results> testSubscriber = mLastFMGatway.getArtist(artistName).test();
        Results_ results = testSubscriber.values().get(0).getResults();
        assertTrue(Integer.parseInt(results.getOpensearchTotalResults()) > 0);
        assertTrue(results.getArtistmatches().getArtist().size() >= dataPageLimit);
        assertEquals(artistName, results.getArtistmatches().getArtist().get(0).getName());
    }

    @Test
    public void  fetchNoAlbums() {
        String album = "XXZZ454B52s";

        TestObserver<Results> testSubscriber = mLastFMGatway.getAlbums(album).test();
        Results_ results = testSubscriber.values().get(0).getResults();
        assertEquals("0", results.getOpensearchTotalResults());
        assertEquals(0, results.getAlbummatches().getAlbum().size());
    }

    @Test
    public void fetchAlbums() {
        String album = "Malpractice";

        TestObserver<Results> testSubscriber = mLastFMGatway.getAlbums(album).test();
        Results_ results = testSubscriber.values().get(0).getResults();
        assertTrue(Integer.parseInt(results.getOpensearchTotalResults()) > 0);
        assertTrue(results.getAlbummatches().getAlbum().size() >= dataPageLimit);
        assertEquals(album, results.getAlbummatches().getAlbum().get(0).getName());
    }

    @Test
    public void  fetchNoTracks() {
        String track = "XXZZ454B52s";

        TestObserver<Results> testSubscriber = mLastFMGatway.getTracks(track).test();
        Results_ results = testSubscriber.values().get(0).getResults();
        assertEquals("0", results.getOpensearchTotalResults());
        assertEquals(0, results.getTrackmatches().getTrack().size());
    }

    @Test
    public void fetchTracks() {
        String track = "She loves you";

        TestObserver<Results> testSubscriber = mLastFMGatway.getTracks(track).test();
        Results_ results = testSubscriber.values().get(0).getResults();
        assertTrue(Integer.parseInt(results.getOpensearchTotalResults()) > 0);
        assertTrue(results.getTrackmatches().getTrack().size() >= dataPageLimit);
        assertTrue(track.equalsIgnoreCase(results.getTrackmatches().getTrack().get(0).getName()));
    }
}
