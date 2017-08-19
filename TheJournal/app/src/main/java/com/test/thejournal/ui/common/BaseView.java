package com.test.thejournal.ui.common;

import android.view.View;

public interface BaseView {

  void showLoading();

  void hideLoading();

  void showNews();

  void hideNews();

  void showEmptyState();

  void hideEmptyState();

  View getView();
}
