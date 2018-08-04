package com.discoid.testsavlastfm.view

import android.view.View
import com.discoid.testsavlastfm.io.model.IResult
import kotlinx.android.synthetic.main.adapter_result_internal.view.*

/**
 * Created by jahsavage on 06/09/2016.
 */
class ResultViewHolder(var view: View) : SelectionHighlighterViewHolder(view) {

    fun populate(result: IResult, searchType: SearchType) {

        var images =  result?.images

        if (images == null || images.size < 1) {
            view.adapter_result_thumbnail_iv.setImageDrawable(null)
        } else {
            PicassoHelper.fetchThumbnail(view.adapter_result_title.context, result?.images?.get(0)?.text, searchType, view.adapter_result_thumbnail_iv)
        }

        view.adapter_result_title.text = result.name

    }

}
