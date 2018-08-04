package com.discoid.testsavlastfm.io

import android.support.test.InstrumentationRegistry
import com.discoid.testsavlastfm.di.modules.NetworkModule
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class LastFMTest {

    private val dataPageLimit = 30

    @Inject
    lateinit var mLastFMGatway: LastFMGateway

    @Before
    fun init() {
        val testAppComponent = DaggerLastFMTestComponent.builder()
                .networkModule(NetworkModule())
                .build()
        testAppComponent.inject(this)
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.discoid.testsavlastfm", appContext.packageName)
    }

    @Test
    fun fetchNoArtists() {
        val artistName = "XXZZ454B52s"

        val testSubscriber = mLastFMGatway.getArtist(artistName).test()
        val results = testSubscriber.values()[0].results
        assertEquals("0", results.opensearchTotalResults)
        assertEquals(0, results.artistmatches.artist.size.toLong())
    }

    @Test
    fun fetchArtists() {
        val artistName = "Sex Pistols"

        val testSubscriber = mLastFMGatway.getArtist(artistName).test()
        val results = testSubscriber.values()[0].results
        assertTrue(Integer.parseInt(results.opensearchTotalResults) > 0)
        assertTrue(results.artistmatches.artist.size >= dataPageLimit)
        assertEquals(artistName, results.artistmatches.artist[0].name)
    }

    @Test
    fun fetchNoAlbums() {
        val album = "XXZZ454B52s"

        val testSubscriber = mLastFMGatway.getAlbums(album).test()
        val results = testSubscriber.values()[0].results
        assertEquals("0", results.opensearchTotalResults)
        assertEquals(0, results.albummatches.album.size.toLong())
    }

    @Test
    fun fetchAlbums() {
        val album = "Malpractice"

        val testSubscriber = mLastFMGatway.getAlbums(album).test()
        val results = testSubscriber.values()[0].results
        assertTrue(Integer.parseInt(results.opensearchTotalResults) > 0)
        assertTrue(results.albummatches.album.size >= dataPageLimit)
        assertEquals(album, results.albummatches.album[0].name)
    }

    @Test
    fun fetchNoTracks() {
        val track = "XXZZ454B52s"

        val testSubscriber = mLastFMGatway.getTracks(track).test()
        val results = testSubscriber.values()[0].results
        assertEquals("0", results.opensearchTotalResults)
        assertEquals(0, results.trackmatches.track.size.toLong())
    }

    @Test
    fun fetchTracks() {
        val track = "She loves you"

        val testSubscriber = mLastFMGatway.getTracks(track).test()
        val results = testSubscriber.values()[0].results
        assertTrue(Integer.parseInt(results.opensearchTotalResults) > 0)
        assertTrue(results.trackmatches.track.size >= dataPageLimit)
        assertTrue(track.equals(results.trackmatches.track[0].name, ignoreCase = true))
    }
}
