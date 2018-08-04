package com.discoid.testsavlastfm.presenter;

import com.discoid.testsavlastfm.io.model.IResult;
import com.discoid.testsavlastfm.view.SearchType;

import java.util.List;

public interface ILastFMPresenterView {

    void showError(Throwable t);
    void show(List<IResult> results, SearchType searchType);
    void showUri(String uri);
}
