package com.discoid.testsavlastfm.io;

import com.discoid.testsavlastfm.io.model.Results;
import com.discoid.testsavlastfm.view.SearchType;

import io.reactivex.Single;

public interface IResultsInteractor {
    Single<Results> getResults(String query, SearchType searchType);
}
