package com.fa.epoxysample

import android.content.Context
import android.util.AttributeSet
import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyRecyclerView
import com.fa.epoxysample.bundles.models.misc.AptoideItemAnimator

/**
 * Base Aptoide Recycler View used for vertical recycler views.
 */
class AptoideRecyclerView : EpoxyRecyclerView {

  constructor(context: Context) : this(context, null)
  constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
    context, attrs,
    defStyleAttr
  )

  init {
    itemAnimator = AptoideItemAnimator()
  }


  fun refreshController(controller: EpoxyController) {
    setController(controller)
    scheduleLayoutAnimation()
  }
}