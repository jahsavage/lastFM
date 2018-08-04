package com.discoid.testsavlastfm.presenter;

import com.discoid.testsavlastfm.io.model.IResult;
import com.discoid.testsavlastfm.view.SearchType;

public interface ILastFMPresenterInputs {

    void clear();
    void start(ILastFMPresenterView view);
    void searchRequest(String query, SearchType searchType);
    void resultsSelected(IResult result);
}
