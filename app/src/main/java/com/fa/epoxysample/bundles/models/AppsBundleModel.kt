package com.fa.epoxysample.bundles.models

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelGroup
import com.fa.epoxysample.R
import com.fa.epoxysample.bundles.models.carousels.AppCarouselModel_
import com.fa.epoxysample.bundles.models.components.AppCardModel_
import com.fa.epoxysample.bundles.models.components.TitleModel_
import com.fa.epoxysample.others.AppHomeEvent
import com.fa.epoxysample.others.HomeEvent
import com.fa.epoxysample.others.model.Application
import com.fa.epoxysample.others.model.HomeBundle
import io.reactivex.subjects.PublishSubject
import java.text.DecimalFormat

class AppsBundleModel(homeBundle: HomeBundle, eventsListener: PublishSubject<HomeEvent>,
                      oneDecimalFormatter: DecimalFormat) :
    EpoxyModelGroup(R.layout.bundle_root_view,
        buildModels(homeBundle, eventsListener, oneDecimalFormatter)) {

  companion object {
    fun buildModels(homeBundle: HomeBundle,
                    eventsListener: PublishSubject<HomeEvent>,
                    oneDecimalFormatter: DecimalFormat): List<EpoxyModel<*>> {

      val models = ArrayList<EpoxyModel<*>>()

      // Adds title view
      models.add(
          TitleModel_()
              .id(homeBundle.title)
              .homeBundle(homeBundle)
              .moreClickListener { model, parentView, clickedView, position ->
                eventsListener.onNext(
                    HomeEvent(model.homeBundle(), 0,
                        HomeEvent.Type.MORE)) // TODO: Retrieve bundlePosition correctly
              }
      )

      // Creates carousel data
      val cardsModel = ArrayList<AppCardModel_>()
      for (app in homeBundle.content) {
        cardsModel.add(
            AppCardModel_()
                .id(homeBundle.tag + (app as Application).appId)
                .bundle(homeBundle)
                .application(app)
                .oneDecimalFormatter(oneDecimalFormatter)
                .clickListener { model, parentView, clickedView, position ->
                  eventsListener.onNext(
                      AppHomeEvent(model.application(), position, model.bundle(), 0,
                          HomeEvent.Type.APP))
                }
        )
      }

      // Adds carousel
      models.add(
          AppCarouselModel_()
              .id(homeBundle.title, homeBundle.tag) // Not a good way to do this
              .initialPrefetchItemCount(5)
              .models(cardsModel))


      return models
    }
  }
}