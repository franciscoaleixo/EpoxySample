package com.fa.epoxysample.bundles.models

import android.graphics.Color
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelGroup
import com.airbnb.epoxy.ModelGroupHolder
import com.fa.epoxysample.R
import com.fa.epoxysample.bundles.models.carousels.SpecialBundleCarousel
import com.fa.epoxysample.bundles.models.carousels.SpecialBundleCarouselModel_
import com.fa.epoxysample.bundles.models.components.BarebonesAppCardModel_
import com.fa.epoxysample.bundles.models.components.EmptyViewModel_
import com.fa.epoxysample.bundles.models.components.TitleTwoModel_
import com.fa.epoxysample.others.AppHomeEvent
import com.fa.epoxysample.others.HomeEvent
import com.fa.epoxysample.others.model.Application
import com.fa.epoxysample.others.model.HomeBundle
import io.reactivex.subjects.PublishSubject
import java.text.DecimalFormat


class SpecialBundleModel(
    bundle: HomeBundle, eventsListener: PublishSubject<HomeEvent>,
    oneDecimalFormatter: DecimalFormat) : EpoxyModelGroup(R.layout.special_bundle_root_view,
    buildModels(bundle, eventsListener, oneDecimalFormatter)) {

  override fun bind(holder: ModelGroupHolder) {
    super.bind(holder)
    holder.rootView.setBackgroundColor(Color.BLACK)

    val rv = holder.rootView.findViewById<SpecialBundleCarousel>(R.id.recycler_view)
    rv?.parallaxHeaderView = holder.rootView.findViewById(R.id.header)
    rv?.parallaxUpperView = holder.rootView.findViewById(R.id.badges)
    rv?.bgOverlay = holder.rootView.findViewById(R.id.bg_overlay)
  }

  companion object {
    fun buildModels(homeBundle: HomeBundle, eventsListener: PublishSubject<HomeEvent>,
                    oneDecimalFormatter: DecimalFormat): List<EpoxyModel<*>> {

      val models = ArrayList<EpoxyModel<*>>()

      models.add(
          TitleTwoModel_()
              .id(homeBundle.title)
              .homeBundle(homeBundle)
              .moreClickListener { model, parentView, clickedView, position ->
                eventsListener.onNext(
                    HomeEvent(
                        model.homeBundle(), 0,
                        HomeEvent.Type.MORE
                    )
                )
              }
      )

      models.add(
          SpecialBundleCarouselModel_()
              .id(homeBundle.title, homeBundle.tag) // Not a good way to do this
              .initialPrefetchItemCount(5)
              .models(getContentModelList(homeBundle, oneDecimalFormatter, eventsListener))
      )


      return models
    }

    private fun getContentModelList(homeBundle: HomeBundle,
                                    oneDecimalFormatter: DecimalFormat,
                                    eventsListener: PublishSubject<HomeEvent>): ArrayList<EpoxyModel<*>> {
      val cardsModel = ArrayList<EpoxyModel<*>>()

      // Add empty space
      cardsModel.add(EmptyViewModel_()
          .id("empty_placeholder")
          .styleBuilder { builder -> builder.layoutWidthDp(246).layoutHeight(MATCH_PARENT) })

      // Add our bundle
      for (app in homeBundle.content) {
        cardsModel.add(
            BarebonesAppCardModel_()
                .id(homeBundle.tag + (app as Application).appId)
                .bundle(homeBundle)
                .application(app)
                .oneDecimalFormatter(oneDecimalFormatter)
                .clickListener { model, parentView, clickedView, position ->
                  eventsListener.onNext(
                      AppHomeEvent(
                          model.application(), position, model.bundle(), 0,
                          HomeEvent.Type.APP
                      )
                  )
                }
        )
      }
      return cardsModel
    }
  }
}