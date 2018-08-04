package com.discoid.testsavlastfm;

import com.discoid.testsavlastfm.io.IResultsInteractor;
import com.discoid.testsavlastfm.io.model.IResult;
import com.discoid.testsavlastfm.io.model.Results;
import com.discoid.testsavlastfm.io.model.Results_;
import com.discoid.testsavlastfm.presenter.ILastFMPresenterInputs;
import com.discoid.testsavlastfm.presenter.ILastFMPresenterView;
import com.discoid.testsavlastfm.presenter.IResultsConverter;
import com.discoid.testsavlastfm.presenter.LastFMLastPresenterImpl;
import com.discoid.testsavlastfm.view.SearchType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class LastFMPresenterTest {

    @Mock
    private ILastFMPresenterView mLastFMPresenterView;

    @Mock
    private IResultsInteractor mIResultsInteractor;

    @Mock
    private IResultsConverter mIResultsConverter;

    @Captor
    ArgumentCaptor<List<IResult>> displayListArgumentCaptor;

    @Captor
    ArgumentCaptor<SearchType> displaySearchType;

    private ILastFMPresenterInputs unt;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        unt = new LastFMLastPresenterImpl(mIResultsInteractor, mIResultsConverter);
    }

    @Test
    public void checkInstance() {
        assertNotNull(unt);
    }

    @Test
    public void checkSearchRequest() {

        String query = "Dr Feelgood";
        SearchType searchType = SearchType.ALBUM;
        Results results = new Results();
        results.setResults(new Results_());
        Single<Results> singleResults = Single.just(results);
        List<IResult> iResultsList = new ArrayList<IResult>();

        given(mIResultsInteractor.getResults(eq(query),eq(searchType))).willReturn(singleResults);
        given(mIResultsConverter.getGeneralResults(any(Results_.class), eq(searchType))).willReturn(iResultsList);

        unt.start(mLastFMPresenterView);
        unt.searchRequest(query, searchType);

        verify(mIResultsConverter).getGeneralResults(any(Results_.class), any(SearchType.class));
        verify(mLastFMPresenterView).show(displayListArgumentCaptor.capture(), displaySearchType.capture());

        assertEquals(iResultsList, displayListArgumentCaptor.getValue());
        assertEquals(searchType, displaySearchType.getValue());

    }
}


