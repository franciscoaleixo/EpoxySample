package com.fa.epoxysample.others;


import com.fa.epoxysample.others.model.Application;

import java.util.List;

public class BundleModel {

  private String title;
  private List<Application> apps;
  private String type;
  private String tag;

  public BundleModel(String title, List<Application> apps, String type, String tag) {
    this.title = title;
    this.apps = apps;
    this.type = type;
    this.tag = tag;
  }

  public BundleModel(){}

  public String getTitle() {
    return title;
  }

  public List<Application> getApps() {
    return apps;
  }

  public String getType() {
    return type;
  }

  public String getTag() {
    return tag;
  }
}
