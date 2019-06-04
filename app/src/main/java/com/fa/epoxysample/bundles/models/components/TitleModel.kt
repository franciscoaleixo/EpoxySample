package com.fa.epoxysample.bundles.models.components

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.fa.epoxysample.R
import com.fa.epoxysample.bundles.models.base.BaseViewHolder
import com.fa.epoxysample.bundles.models.components.TitleModel.TitleViewHolder
import com.fa.epoxysample.others.model.HomeBundle

@EpoxyModelClass(layout = R.layout.home_bundle_header)
abstract class TitleModel : EpoxyModelWithHolder<TitleViewHolder>() {

  @EpoxyAttribute
  var homeBundle: HomeBundle? = null

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var moreClickListener: View.OnClickListener? = null

  override fun bind(holder: TitleViewHolder) {
    holder.bundleTitleView.text = homeBundle?.title
    holder.moreButton.setOnClickListener(moreClickListener)
  }

  override fun unbind(holder: TitleViewHolder) {
    holder.moreButton.setOnClickListener(null)
  }

  class TitleViewHolder : BaseViewHolder() {
    val bundleTitleView by bind<TextView>(R.id.bundle_title)
    val moreButton by bind<Button>(R.id.bundle_more)
  }
}

