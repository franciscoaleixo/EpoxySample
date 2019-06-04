package com.fa.epoxysample.bundles.models.carousels

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SnapHelper
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView
import com.fa.epoxysample.bundles.models.misc.SnapToStartHelper

@ModelView(saveViewState = true, autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class SpecialBundleCarousel(context: Context) : Carousel(context) {

  var parallaxHeaderView: View? = null
  var parallaxUpperView: View? = null
  var bgOverlay: View? = null

  override fun getSnapHelperFactory(): SnapHelperFactory? {
    return object : Carousel.SnapHelperFactory() {
      override fun buildSnapHelper(context: Context): SnapHelper {
        return SnapToStartHelper()
      }
    }
  }

  override fun onScrolled(dx: Int, dy: Int) {
    super.onScrolled(dx, dy)

    val layoutManager = (layoutManager as LinearLayoutManager)
    if (layoutManager.findFirstVisibleItemPosition() == 0) {
      val firstView = layoutManager.findViewByPosition(0)
      firstView?.let { view ->
        parallaxHeaderView?.translationX = (view.left.toFloat() * 0.3f)
        parallaxUpperView?.translationX = (view.left.toFloat() * 0.13f)
        bgOverlay?.alpha = Math.abs(view.left).toFloat() / view.width
      }
    }
  }

}