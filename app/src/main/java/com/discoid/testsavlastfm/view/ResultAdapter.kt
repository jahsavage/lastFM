package com.discoid.testsavlastfm.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.discoid.testsavlastfm.R

import com.discoid.testsavlastfm.io.model.IResult

/**
 * Created by jahsavage on 06/09/2016.
 */
class ResultAdapter : BaseAdapter() {

    private var mResults: List<IResult>? = null
    private val mSelectionListener: SelectionListener
    private var mResultClickListener: OnResultClickListener? = null
    private var mSearchType : SearchType? = null

    init {
        mSelectionListener = SelectionListener { position ->
            if (mResultClickListener != null) {
                mResultClickListener!!.onClick(mResults!![position])
            }
        }
    }

    override fun getItemDataCount(): Int {
        return if (mResults == null) {
            0
        } else {
            mResults!!.size
        }
    }

    override fun getHeaderViewType(): Int {
        return BaseAdapter.VIEW_TYPE_EMPTY
    }

    override fun getFooterViewType(): Int {
        return BaseAdapter.VIEW_TYPE_EMPTY
    }

    override fun getItemDataViewType(position: Int): Int {
        return 0
    }

    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_result, parent, false)
        val resultViewHolder = ResultViewHolder(view)
        resultViewHolder.setSelectionListener(mSelectionListener)

        return resultViewHolder
    }

    override fun onCreateHeaderViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        return null
    }

    override fun onCreateFooterViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
        return null
    }

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val resultViewHolder = holder as ResultViewHolder
        resultViewHolder.populate(mResults!![position], mSearchType!!)
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun onBindFooterViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    fun setResults(results: List<IResult>, searchType : SearchType) {
        mResults = results
        mSearchType = searchType
        notifyDataSetChanged();
    }

    fun setOnResultClickListener(resultClickListener: OnResultClickListener) {
        mResultClickListener = resultClickListener
    }

}
