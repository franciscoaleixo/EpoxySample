package com.fa.epoxysample.bundles.models.components

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.fa.epoxysample.R
import com.fa.epoxysample.bundles.models.base.BaseViewHolder
import com.fa.epoxysample.bundles.models.components.LargeAppCardModel.LargeAppCardHolder
import com.fa.epoxysample.others.ImageLoader
import com.fa.epoxysample.others.model.Application
import com.fa.epoxysample.others.model.HomeBundle
import java.text.DecimalFormat

@EpoxyModelClass(layout = R.layout.feature_graphic_home_item)
abstract class LargeAppCardModel : EpoxyModelWithHolder<LargeAppCardHolder>() {

  @EpoxyAttribute
  var application: Application =
      Application()

  @EpoxyAttribute
  var bundle: HomeBundle? = null

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var clickListener: View.OnClickListener? = null

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var oneDecimalFormatter: DecimalFormat? = null


  override fun bind(holder: LargeAppCardHolder) {
    val context = holder.itemView.context

    holder.nameTextView.text = application.name
    ImageLoader.with(context)
        .load(application.featureGraphic, R.drawable.placeholder_brick,
            holder.featureGraphic)
    ImageLoader.with(context)
        .load(application.icon, R.drawable.placeholder_square, holder.appIcon)
    application.rating.let { rating ->
      if (rating == 0f) {
        holder.rating.setText(R.string.appcardview_title_no_stars)
      } else {
        holder.rating.text = oneDecimalFormatter?.format(rating.toDouble())
      }
    }

    holder.itemView.setOnClickListener(clickListener)
  }

  override fun unbind(holder: LargeAppCardHolder) {
    holder.itemView.setOnClickListener(null)
    holder.appIcon.setImageDrawable(null)
    holder.featureGraphic.setImageDrawable(null)
  }

  class LargeAppCardHolder : BaseViewHolder() {
    val nameTextView by bind<TextView>(R.id.app_name)
    val featureGraphic by bind<ImageView>(R.id.featured_graphic)
    val rating by bind<TextView>(R.id.rating_label)
    val appIcon by bind<ImageView>(R.id.app_icon)
    val itemView by bind<View>(R.id.brick_app_item)
  }
}