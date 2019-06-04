package com.fa.epoxysample.others;

import com.fa.epoxysample.others.model.HomeBundle;

/**
 * Created by jdandrade on 12/03/2018.
 */

public class HomeEvent {
  private final HomeBundle bundle;
  private final int bundlePosition;
  private final Type clickType;

  public HomeEvent(HomeBundle bundle, int bundlePosition, Type clickType) {
    this.bundle = bundle;
    this.bundlePosition = bundlePosition;
    this.clickType = clickType;
  }

  public HomeBundle getBundle() {
    return bundle;
  }

  public int getBundlePosition() {
    return bundlePosition;
  }

  public Type getType() {
    return clickType;
  }

  public enum Type {
    MORE, MORE_TOP, APP, AD, SCROLL_RIGHT, REWARD_APP, KNOW_MORE, DISMISS_BUNDLE, SOCIAL_INSTALL, EDITORIAL, INSTALL_WALLET, NO_OP, REACT_SINGLE_PRESS, REACT_LONG_PRESS, REACTION, POPUP_DISMISS
  }
}
