package com.test.thejournal.ui.home.core.view;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import com.test.thejournal.R;

public class DefaultHomeView extends LinearLayout implements HomeView {

  public DefaultHomeView(Context context) {
    super(context);
    inflate(context, R.layout.home_view, this);
  }

  @Override
  public View getView() {
    return this;
  }
}
