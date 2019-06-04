package com.fa.epoxysample.bundles.models.components

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.fa.epoxysample.R
import com.fa.epoxysample.bundles.models.base.BaseViewHolder
import com.fa.epoxysample.others.AppSecondaryInfoViewHolder
import com.fa.epoxysample.others.ImageLoader
import com.fa.epoxysample.others.model.Application
import com.fa.epoxysample.others.model.HomeBundle
import java.text.DecimalFormat

@EpoxyModelClass(layout = R.layout.app_barebones_item)
abstract class BarebonesAppCardModel :
    EpoxyModelWithHolder<BarebonesAppCardModel.BarebonesAppCardHolder>() {

  @EpoxyAttribute
  var application: Application =
      Application()

  @EpoxyAttribute
  var bundle: HomeBundle? = null

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var clickListener: View.OnClickListener? = null

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var oneDecimalFormatter: DecimalFormat? = null

  override fun createNewHolder(): BarebonesAppCardHolder {
    return BarebonesAppCardHolder()
  }

  override fun bind(holder: BarebonesAppCardHolder) {
    val context = holder.itemView.context

    holder.nameTextView.text = application.name
    ImageLoader.with(context)
        .load(application.icon, R.drawable.placeholder_square, holder.appIcon)

    val appSecondaryInfoViewHolder =
        AppSecondaryInfoViewHolder(holder.itemView, oneDecimalFormatter)
    appSecondaryInfoViewHolder.setInfo(application.hasBilling, application.rating, true, false)

    if (bundle?.type == HomeBundle.BundleType.SPECIAL_BUNDLE) {
      holder.nameTextView.setTextColor(Color.WHITE)
      appSecondaryInfoViewHolder.setColor(Color.WHITE)
    }

    holder.itemView.setOnClickListener(clickListener)
  }

  override fun unbind(holder: BarebonesAppCardHolder) {
    holder.itemView.setOnClickListener(null)
    holder.appIcon.setImageDrawable(null)
  }

  class BarebonesAppCardHolder : BaseViewHolder() {
    val nameTextView by bind<TextView>(R.id.name)
    val appIcon by bind<ImageView>(R.id.icon)
    val itemView by bind<View>(R.id.root_view)
  }
}