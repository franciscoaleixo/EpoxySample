package com.fa.epoxysample.bundles.models.carousels

import android.content.Context
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView

@ModelView(saveViewState = true, autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class EditorsCarousel(context: Context) : Carousel(context) {

  override fun getSnapHelperFactory(): SnapHelperFactory? {
    return object : Carousel.SnapHelperFactory() {
      override fun buildSnapHelper(context: Context): SnapHelper {
        return PagerSnapHelper()
      }
    }
  }
}