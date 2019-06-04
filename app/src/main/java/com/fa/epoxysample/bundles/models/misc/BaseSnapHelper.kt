package com.fa.epoxysample.bundles.models.misc

import android.view.animation.DecelerateInterpolator
import android.widget.Scroller
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

abstract class BaseSnapHelper : SnapHelper() {

  private var mGravityScroller: Scroller? = null

  override fun attachToRecyclerView(recyclerView: RecyclerView?) {
    super.attachToRecyclerView(recyclerView)

    recyclerView?.run {
      mGravityScroller = Scroller(context, DecelerateInterpolator())
    }

  }
  override fun calculateScrollDistance(velocityX: Int, velocityY: Int): IntArray {
    mGravityScroller?.let { scroller ->
      val outDist = IntArray(2)
      scroller.fling(0, 0, velocityX, velocityY,
          Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE)
      outDist[0] = scroller.finalX
      outDist[1] = scroller.finalY
      return outDist
    }
    return super.calculateScrollDistance(velocityX, velocityY)
  }
}