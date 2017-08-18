package com.test.thejournal.ui.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.test.thejournal.application.TheJournalApplication;
import com.test.thejournal.ui.home.builder.DaggerHomeComponent;
import com.test.thejournal.ui.home.builder.HomeModule;
import com.test.thejournal.ui.home.core.presenter.HomePresenter;
import com.test.thejournal.ui.home.core.view.HomeView;
import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity {

  @Inject
  HomeView view;

  @Inject
  HomePresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    DaggerHomeComponent.builder()
        .theJournalComponent(((TheJournalApplication) getApplication()).getAppComponent())
        .homeModule(new HomeModule(this))
        .build()
        .inject(this);

    setContentView(view.getView());
    presenter.create();
  }
}
