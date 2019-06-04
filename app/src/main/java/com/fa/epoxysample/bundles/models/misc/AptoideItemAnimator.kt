package com.fa.epoxysample.bundles.models.misc

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.recyclerview.widget.RecyclerView

class AptoideItemAnimator : BaseItemAnimator() {

  override fun animateRemoveImpl(holder: RecyclerView.ViewHolder) {
    val view = holder.itemView
    val animation = view.animate()
    mRemoveAnimations.add(holder)
    animation.setDuration(200)
      .translationY(holder.itemView.height.toFloat())
      .alpha(0f)
      .setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animator: Animator) {
          dispatchRemoveStarting(holder)
        }

        override fun onAnimationEnd(animator: Animator) {
          animation.setListener(null)
          view.alpha = 1f
          dispatchRemoveFinished(holder)
          mRemoveAnimations.remove(holder)
          dispatchFinishedWhenDone()
        }
      })
      .start()
  }
}