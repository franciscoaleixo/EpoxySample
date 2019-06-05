package com.fa.epoxysample.bundles.models.components

import android.graphics.Color
import android.widget.TextView
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.fa.epoxysample.R
import com.fa.epoxysample.bundles.models.base.BaseViewHolder

@EpoxyModelClass(layout = R.layout.app_home_item)
abstract class ChipModel : EpoxyModelWithHolder<ChipModel.ChipViewHolder>() {

  override fun bind(holder: ChipViewHolder) {
    holder.chip.setBackgroundColor(Color.TRANSPARENT)
    holder.chip.setText("Test")
  }

  class ChipViewHolder : BaseViewHolder() {
    val chip by bind<TextView>(R.id.chip)
  }
}