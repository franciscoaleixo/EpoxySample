package com.fa.epoxysample.bundles.models.base

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * A pattern for easier view binding with an [EpoxyHolder]
 *
 */
abstract class BaseViewHolder : EpoxyHolder() {
  private lateinit var view: View

  override fun bindView(itemView: View) {
    view = itemView
  }

  protected fun <V : View> bind(id: Int): ReadOnlyProperty<BaseViewHolder, V> =
      Lazy { holder: BaseViewHolder, prop ->
        holder.view.findViewById(id) as V?
            ?: throw IllegalStateException("View ID $id for '${prop.name}' not found.")
      }

  /**
   * Taken from Kotterknife.
   * https://github.com/JakeWharton/kotterknife
   */
  private class Lazy<V>(
      private val initializer: (BaseViewHolder, KProperty<*>) -> V
  ) : ReadOnlyProperty<BaseViewHolder, V> {
    private object EMPTY

    private var value: Any? = EMPTY

    override fun getValue(thisRef: BaseViewHolder, property: KProperty<*>): V {
      if (value == EMPTY) {
        value = initializer(thisRef, property)
      }
      @Suppress("UNCHECKED_CAST")
      return value as V
    }
  }
}