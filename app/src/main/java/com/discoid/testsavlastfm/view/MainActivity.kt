package com.discoid.testsavlastfm.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.discoid.testsavlastfm.R
import com.discoid.testsavlastfm.io.model.IResult
import com.discoid.testsavlastfm.presenter.ILastFMPresenterInputs
import com.discoid.testsavlastfm.presenter.ILastFMPresenterView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener, ILastFMPresenterView, OnResultClickListener {

    @Inject
    lateinit var mLastFMPresenter : ILastFMPresenterInputs

    @Inject
    lateinit var mResultAdapter: ResultAdapter

    private lateinit var mSearchView: SearchView
    private lateinit var mSearchTypes : Array<MenuItem>
    private var mIsDefaultMenuItemSet = false

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Initialise recycle view
        val llm = LinearLayoutManager(this)
        llm.orientation = LinearLayoutManager.VERTICAL
        results_lsit_rv.layoutManager = llm
        results_lsit_rv.adapter = mResultAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        var searchItem = menu.findItem(R.id.action_search)
        searchItem.isVisible = true

        mSearchView = searchItem.actionView as SearchView
        mSearchView.setQueryHint(getString(R.string.search_hint))
        mSearchView.setOnQueryTextListener(this)

        var searchByArtist = menu.findItem(R.id.menu_search_by_artist)
        var searchByAlbum = menu.findItem(R.id.menu_search_by_album)
        var searchByTrack = menu.findItem(R.id.menu_search_by_track)

        mSearchTypes = arrayOf(searchByArtist,searchByAlbum,searchByTrack)
        mResultAdapter.setOnResultClickListener(this)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.menu_search_by_artist -> item.setChecked(true)
            R.id.menu_search_by_album -> item.setChecked(true)
            R.id.menu_search_by_track -> item.setChecked(true)
        }
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        var searchType = getSearchType()
        Timber.d("Searching for ${mSearchView.query} search type ${searchType.name}")
        mLastFMPresenter.searchRequest(query, searchType)
        return false
    }

    private fun getSearchType() : SearchType {
        mSearchTypes.forEach { if (it.isChecked) return SearchType.match(it.itemId)}

        return SearchType.ARTIST
    }
    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onMenuOpened(featureId: Int, menu: Menu?): Boolean {
        // Unable to set default in advance of menu opening - a funny bug I believe
        if (!mIsDefaultMenuItemSet) {
            mSearchTypes[0].isChecked = true
            mIsDefaultMenuItemSet = true
        }
        return super.onMenuOpened(featureId, menu)
    }
    override fun show(results: List<IResult>, searchType: SearchType) {
       Timber.d("Received results")
        mResultAdapter.setResults(results, searchType)

    }

    override fun showError(t: Throwable) {
        Timber.e("Error ${t.message}",t)
    }

    override fun onResume() {
        super.onResume()
        mLastFMPresenter.start(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mLastFMPresenter.clear()
    }

    override fun onClick(result: IResult?) {
        mLastFMPresenter.resultsSelected(result)
    }

    override fun showUri(uri: String?) {
        uri?.let {
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(uri)
            startActivity(i)
        }
    }
}
