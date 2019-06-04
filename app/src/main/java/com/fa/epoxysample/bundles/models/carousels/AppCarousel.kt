package com.fa.epoxysample.bundles.models.carousels

import android.content.Context
import androidx.recyclerview.widget.SnapHelper
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.ModelView
import com.fa.epoxysample.bundles.models.misc.SnapToStartHelper


@ModelView(saveViewState = true, autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
open class AppCarousel(context: Context) : Carousel(context) {

  override fun getSnapHelperFactory(): SnapHelperFactory? {
    return null
  }

}