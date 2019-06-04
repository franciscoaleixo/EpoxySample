package com.fa.epoxysample.bundles.models.components

import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.fa.epoxysample.R
import com.fa.epoxysample.bundles.models.base.BaseViewHolder
import com.fa.epoxysample.others.model.HomeBundle

@EpoxyModelClass(layout = R.layout.home_bundle_header_two)
abstract class TitleTwoModel : EpoxyModelWithHolder<TitleTwoModel.TitleTwoViewHolder>() {

  @EpoxyAttribute
  var homeBundle: HomeBundle? = null

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var moreClickListener: View.OnClickListener? = null

  override fun bind(holder: TitleTwoViewHolder) {
    holder.bundleTitleView.text = homeBundle?.title
    holder.bundleTitleSubView.text = "Conquer the ultimate throne" // Don't do this obviously
    holder.moreButton.setOnClickListener(moreClickListener)

    // The color should be returned in the bundle as a parameter, this is just hacky testing :~)
    if (homeBundle?.type == HomeBundle.BundleType.SPECIAL_BUNDLE) {
      holder.bundleTitleView.setTextColor(Color.WHITE)
      holder.bundleTitleSubView.setTextColor(Color.WHITE)
    }
  }

  override fun unbind(holder: TitleTwoViewHolder) {
    holder.moreButton.setOnClickListener(null)
  }

  class TitleTwoViewHolder : BaseViewHolder() {
    val bundleTitleView by bind<TextView>(R.id.bundle_title)
    val bundleTitleSubView by bind<TextView>(R.id.bundle_title_sub)
    val moreButton by bind<Button>(R.id.bundle_more)
  }
}
