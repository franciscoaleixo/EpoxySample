package com.fa.epoxysample.bundles.models

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.EpoxyModelGroup
import com.airbnb.epoxy.ModelGroupHolder
import com.fa.epoxysample.R
import com.fa.epoxysample.bundles.models.carousels.EditorsCarouselModel_
import com.fa.epoxysample.bundles.models.components.LargeAppCardModel_
import com.fa.epoxysample.bundles.models.components.TitleModel_
import com.fa.epoxysample.others.AppHomeEvent
import com.fa.epoxysample.others.HomeEvent
import com.fa.epoxysample.others.model.Application
import com.fa.epoxysample.others.model.HomeBundle
import io.reactivex.subjects.PublishSubject
import java.text.DecimalFormat

class EditorsBundleModel(homeBundle: HomeBundle, eventsListener: PublishSubject<HomeEvent>,
                         oneDecimalFormatter: DecimalFormat) :
    EpoxyModelGroup(R.layout.bundle_root_view,
        buildModels(homeBundle, eventsListener, oneDecimalFormatter)) {

  override fun bind(holder: ModelGroupHolder, payloads: MutableList<Any>) {
    super.bind(holder, payloads)
  }

  companion object {
    fun buildModels(homeBundle: HomeBundle,
                    eventsListener: PublishSubject<HomeEvent>,
                    oneDecimalFormatter: DecimalFormat): List<EpoxyModel<*>> {

      val models = ArrayList<EpoxyModel<*>>()

      models.add(TitleModel_()
          .id(homeBundle.title)
          .homeBundle(homeBundle)
          .moreClickListener { model, parentView, clickedView, position ->
            eventsListener.onNext(
                HomeEvent(model.homeBundle(), 0,
                    HomeEvent.Type.MORE)) // TODO: Retrieve bundlePosition correctly
          }
      )

      val cardsModel = ArrayList<LargeAppCardModel_>()
      for (app in homeBundle.content) {
        cardsModel.add(LargeAppCardModel_()
            .id((app as Application).appId)
            .bundle(homeBundle)
            .application(app)
            .oneDecimalFormatter(oneDecimalFormatter)
            .clickListener { model, parentView, clickedView, position ->
              eventsListener.onNext(AppHomeEvent(model.application(), position, model.bundle(), 0,
                  HomeEvent.Type.APP))
            }
        )
      }

      models.add(EditorsCarouselModel_()
          .id(homeBundle.tag)
          .initialPrefetchItemCount(3)
          .models(cardsModel))

      return models
    }
  }

}
