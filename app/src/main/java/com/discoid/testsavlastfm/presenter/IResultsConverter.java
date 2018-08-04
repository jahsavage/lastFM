package com.discoid.testsavlastfm.presenter;

import com.discoid.testsavlastfm.io.model.IResult;
import com.discoid.testsavlastfm.io.model.Results_;
import com.discoid.testsavlastfm.view.SearchType;

import java.util.List;

public interface IResultsConverter {
    List<IResult> getGeneralResults(Results_ results, SearchType searchType);
}
