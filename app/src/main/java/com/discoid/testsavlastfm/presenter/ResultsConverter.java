package com.discoid.testsavlastfm.presenter;

import com.discoid.testsavlastfm.io.model.Album;
import com.discoid.testsavlastfm.io.model.Artist;
import com.discoid.testsavlastfm.io.model.IResult;
import com.discoid.testsavlastfm.io.model.Results_;
import com.discoid.testsavlastfm.io.model.Track;
import com.discoid.testsavlastfm.view.SearchType;

import java.util.ArrayList;
import java.util.List;

public class ResultsConverter implements IResultsConverter{

    public List<IResult> getGeneralResults(Results_ results, SearchType searchType) {

        switch(searchType) {
            case ARTIST:
                return convertArtists(results.getArtistmatches().getArtist());
            case ALBUM:
                return convertAlbums(results.getAlbummatches().getAlbum());
            case TRACK:
                return convertTracks(results.getTrackmatches().getTrack());
        }

        return null;
    }

    private List<IResult> convertTracks(List<Track> tracks) {
        List<IResult> list = new ArrayList<IResult>();

        for (Track track :  tracks) {
            Result result = new Result();
            result.setName(track.getName());
            result.setImages(track.getImage());
            result.setUrl(track.getUrl());
            result.setMbId(track.getMbid());
            list.add(result);
        }

        return list;

    }

    private List<IResult> convertAlbums(List<Album> albums) {

        List<IResult> list = new ArrayList<IResult>();

        for (Album album :  albums) {
            Result result = new Result();
            result.setName(album.getName());
            result.setImages(album.getImage());
            result.setUrl(album.getUrl());
            result.setMbId(album.getMbid());
            list.add(result);
        }

        return list;
    }

    private List<IResult> convertArtists(List<Artist> artists) {

        List<IResult> list = new ArrayList<IResult>();

        for (Artist artist :  artists) {
            Result result = new Result();
            result.setName(artist.getName());
            result.setImages(artist.getImage());
            result.setUrl(artist.getUrl());
            result.setMbId(artist.getMbid());
            list.add(result);
        }

        return list;
    }
}
