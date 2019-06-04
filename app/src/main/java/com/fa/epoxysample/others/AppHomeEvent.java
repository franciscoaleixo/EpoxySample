package com.fa.epoxysample.others;

import com.fa.epoxysample.others.model.Application;
import com.fa.epoxysample.others.model.HomeBundle;

/**
 * Created by jdandrade on 16/04/2018.
 */

public class AppHomeEvent extends HomeEvent {
  private final Application app;
  private final int appPosition;

  public AppHomeEvent(Application app, int appPosition, HomeBundle bundle, int bundlePosition,
                      Type clickType) {
    super(bundle, bundlePosition, clickType);
    this.app = app;
    this.appPosition = appPosition;
  }

  public Application getApp() {
    return app;
  }

  public int getAppPosition() {
    return appPosition;
  }
}
