package com.fa.epoxysample.others.model;

import com.fa.epoxysample.others.BundleModel;

import java.util.List;

/**
 * Created by jdandrade on 07/03/2018.
 */

public class AppBundle implements HomeBundle {

  private final String title;
  private final List<Application> apps;
  private final BundleType type;
  private final String tag;

  public AppBundle(String title, List<Application> apps, BundleType type, String tag) {
    this.title = title;
    this.apps = apps;
    this.type = type;
    this.tag = tag;
  }

  public AppBundle(BundleModel bundleModel) {
    this.title = bundleModel.getTitle();
    this.apps = bundleModel.getApps();
    this.type = getBundleType(bundleModel.getType());
    this.tag = bundleModel.getTag();
  }

  private BundleType getBundleType(String type){
    if(type.equals(BundleType.EDITORS.name())){
      return BundleType.EDITORS;
    }
    if(type.equals(BundleType.SPECIAL_BUNDLE.name())){
      return BundleType.SPECIAL_BUNDLE;
    }
    return BundleType.APPS;
  }


  public String getTitle() {
    return title;
  }

  @Override public List<?> getContent() {
    return apps;
  }

  @Override public BundleType getType() {
    return type;
  }

  @Override public String getTag() {
    return tag;
  }

  public List<Application> getApps() {
    return apps;
  }
}
