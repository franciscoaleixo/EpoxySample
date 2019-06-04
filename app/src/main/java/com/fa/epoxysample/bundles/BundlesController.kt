package com.fa.epoxysample.bundles

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.Typed2EpoxyController
import com.fa.epoxysample.bundles.models.AppsBundleModel
import com.fa.epoxysample.bundles.models.EditorsBundleModel
import com.fa.epoxysample.bundles.models.SpecialBundleModel
import com.fa.epoxysample.bundles.models.components.ProgressViewModel_
import com.fa.epoxysample.others.HomeEvent
import com.fa.epoxysample.others.model.HomeBundle
import io.reactivex.subjects.PublishSubject
import java.text.DecimalFormat

class BundlesController : Typed2EpoxyController<List<HomeBundle>, Boolean>(
  EpoxyAsyncUtil.getAsyncBackgroundHandler(),
  EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {

  private val eventListener = PublishSubject.create<HomeEvent>()
  val oneDecimalFormatter: DecimalFormat = DecimalFormat("0.0")


  override fun buildModels(data: List<HomeBundle>, hasMore: Boolean) {
    for (homeBundle in data) {
      when (homeBundle.type) {
        HomeBundle.BundleType.EDITORS -> add(
          EditorsBundleModel(homeBundle, eventListener, oneDecimalFormatter)
        )
        HomeBundle.BundleType.APPS -> add(
          AppsBundleModel(homeBundle, eventListener, oneDecimalFormatter)
        )
        HomeBundle.BundleType.SPECIAL_BUNDLE -> add(
          SpecialBundleModel(homeBundle, eventListener, oneDecimalFormatter)
        )
        else -> Unit
      }
    }

    ProgressViewModel_().id("progress").addIf(hasMore, this)
  }

  override fun setData(homeBundles: List<HomeBundle>, hasMore: Boolean) {
    super.setData(homeBundles, hasMore)
  }
}