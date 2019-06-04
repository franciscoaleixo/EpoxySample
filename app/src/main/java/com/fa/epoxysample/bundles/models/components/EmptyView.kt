package com.fa.epoxysample.bundles.models.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import com.airbnb.epoxy.ModelView
import com.airbnb.paris.annotations.Style
import com.airbnb.paris.annotations.Styleable
import com.airbnb.paris.extensions.emptyViewStyle
import com.airbnb.paris.extensions.layoutHeight
import com.airbnb.paris.extensions.layoutWidth

@Styleable
@ModelView
class EmptyView : View {

  constructor(context: Context) : this(context, null)
  constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs,
      defStyleAttr)

  companion object {
    @Style(isDefault = true)
    val defaultStyle = emptyViewStyle {
      layoutWidth(ViewGroup.LayoutParams.MATCH_PARENT)
      layoutHeight(ViewGroup.LayoutParams.WRAP_CONTENT)
    }
  }
}