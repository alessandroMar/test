package com.test.thejournal.ui.home.wireframe;

import android.app.Activity;

public class DefaultHomeWireframe implements HomeWireframe {

  private Activity activity;

  public DefaultHomeWireframe(Activity activity) {

    this.activity = activity;
  }

  @Override
  public void navigateToDetail() {
    //todo add navigation to detail when it will be defined
  }
}
