package com.discoid.testsavlastfm.presenter;

import com.discoid.testsavlastfm.io.model.IResult;
import com.discoid.testsavlastfm.io.model.Image;

import java.util.List;

public class Result implements IResult {

    String name;
    String url;
    String mbId;
    List<Image> mImages;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getMbId() {
        return mbId;
    }

    public void setMbId(String mbId) {
        this.mbId = mbId;
    }

    @Override
    public List<Image> getImages() {
        return mImages;
    }

    public void setImages(List<Image> images) {
        mImages = images;
    }
}
