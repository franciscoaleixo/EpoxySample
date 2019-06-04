package com.fa.epoxysample.bundles.models.components

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.fa.epoxysample.R
import com.fa.epoxysample.bundles.models.base.BaseViewHolder
import com.fa.epoxysample.others.AppSecondaryInfoViewHolder
import com.fa.epoxysample.others.ImageLoader
import com.fa.epoxysample.others.model.Application
import com.fa.epoxysample.others.model.HomeBundle
import java.text.DecimalFormat

@EpoxyModelClass(layout = R.layout.app_home_item)
abstract class AppCardModel : EpoxyModelWithHolder<AppCardModel.AppCardHolder>() {

  @EpoxyAttribute
  var application: Application =
      Application()

  @EpoxyAttribute
  var bundle: HomeBundle? = null

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var clickListener: View.OnClickListener? = null

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  var oneDecimalFormatter: DecimalFormat? = null

  override fun bind(holder: AppCardHolder) {
    val context = holder.itemView.context

    holder.nameTextView.text = application.name
    ImageLoader.with(context)
        .load(application.icon, R.drawable.placeholder_square, holder.appIcon)

    val appSecondaryInfoViewHolder =
        AppSecondaryInfoViewHolder(holder.itemView, oneDecimalFormatter)
    appSecondaryInfoViewHolder.setInfo(application.hasBilling, application.rating, true, false)

    holder.itemView.setOnClickListener(clickListener)
  }

  /**
   * Override this if we want to use partial binds
   */
  override fun bind(holder: AppCardHolder, previouslyBoundModel: EpoxyModel<*>) {
    this.bind(holder)
  }

  override fun unbind(holder: AppCardHolder) {
    holder.itemView.setOnClickListener(null)
    holder.appIcon.setImageDrawable(null)
  }

  class AppCardHolder : BaseViewHolder() {
    val nameTextView by bind<TextView>(R.id.name)
    val appIcon by bind<ImageView>(R.id.icon)
    val itemView by bind<View>(R.id.app_card_view)
  }
}