package com.discoid.testsavlastfm.presenter;

import com.discoid.testsavlastfm.io.IResultsInteractor;
import com.discoid.testsavlastfm.io.model.IResult;
import com.discoid.testsavlastfm.io.model.Results;
import com.discoid.testsavlastfm.view.SearchType;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class LastFMLastPresenterImpl implements ILastFMPresenterInputs {

    private ILastFMPresenterView mILastFMPresenterView;
    private IResultsInteractor mIResultsInteractor;
    private IResultsConverter mResultsConverter;
    private CompositeDisposable mCompositeDisposable;

    public LastFMLastPresenterImpl(IResultsInteractor resultsInteractor, IResultsConverter resultsConverter) {
        mIResultsInteractor = resultsInteractor;
        mResultsConverter = resultsConverter;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void start(ILastFMPresenterView view) {
        mILastFMPresenterView = view;
    }

    @Override
    public void searchRequest(String query, SearchType searchType) {
        Disposable disposable = mIResultsInteractor.getResults(query, searchType)
                .subscribe(results -> processResults(results,searchType), mILastFMPresenterView::showError);
        mCompositeDisposable.add(disposable);
    }

    private void processResults(Results results, SearchType searchType) {
        List<IResult> list = mResultsConverter.getGeneralResults(results.getResults(), searchType);
        mILastFMPresenterView.show(list, searchType);
    }

    @Override
    public void resultsSelected(IResult result) {
        mILastFMPresenterView.showUri(result.getUrl());
    }

    @Override
    public void clear() {
        mCompositeDisposable.clear();
        mILastFMPresenterView = null;
        mIResultsInteractor = null;
    }
}
