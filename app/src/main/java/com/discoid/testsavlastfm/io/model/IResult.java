package com.discoid.testsavlastfm.io.model;

import java.util.List;

public interface IResult {
    String getName();
    List<Image> getImages();
    String getUrl();
    String getMbId();
}
