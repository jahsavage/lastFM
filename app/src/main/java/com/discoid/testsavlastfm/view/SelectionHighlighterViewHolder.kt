package com.discoid.testsavlastfm.view

import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.View
import java.lang.ref.WeakReference

/**
 * Created by paul on 29/10/2016.
 */

open class SelectionHighlighterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private var mSelectionListener: WeakReference<SelectionListener>? = null

    init {
        itemView.setOnClickListener(this)
    }

    fun setSelectionListener(selectionListener: SelectionListener) {
        mSelectionListener = WeakReference(selectionListener)
    }

    fun getSelectionListener() : WeakReference<SelectionListener>? = mSelectionListener

    override fun onClick(view: View?) {
        showInteraction(view);
        if (mSelectionListener != null) {
            mSelectionListener!!.get()?.selected(adapterPosition)
        }
    }

    fun showInteraction(view: View?) {
        view!!.isSelected = true
        val unselect = Runnable {
            if (view.visibility == View.VISIBLE) {
                view.isSelected = false
            }
        }
        Handler().postDelayed(unselect, HIGHLIGHT_DURATION)
    }

    companion object {

        private val HIGHLIGHT_DURATION = 400L
    }
}