package com.fa.epoxysample.others.model;

import java.util.List;

public interface HomeBundle {

  String getTitle();

  List<?> getContent();

  BundleType getType();

  String getTag();

  enum BundleType {
    EDITORS, APPS, ADS, UNKNOWN, LOADING, STORE, INFO_BUNDLE, APPCOINS_ADS, EDITORIAL, SMALL_BANNER, WALLET_ADS_OFFER, TOP, SPECIAL_BUNDLE;

    public boolean isApp() {
      return this.equals(APPS) || this.equals(EDITORS) || this.equals(ADS) || this.equals(
          APPCOINS_ADS);
    }
  }
}
