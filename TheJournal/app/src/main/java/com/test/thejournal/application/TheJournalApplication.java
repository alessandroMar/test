package com.test.thejournal.application;

import android.app.Application;
import com.test.thejournal.application.builder.DaggerTheJournalComponent;
import com.test.thejournal.application.builder.TheJournalComponent;
import com.test.thejournal.application.builder.TheJournalModule;
import com.test.thejournal.network.NetworkModule;

public class TheJournalApplication extends Application {

  private TheJournalComponent appComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    if (appComponent == null) {
      appComponent = DaggerTheJournalComponent.builder().theJournalModule(new TheJournalModule(this)).
          networkModule(new NetworkModule()).build();
    }
  }

  public TheJournalComponent getAppComponent() {
    if (appComponent == null) {
      appComponent = DaggerTheJournalComponent.builder().theJournalModule(new TheJournalModule(this)).
          networkModule(new NetworkModule()).build();
    }
    return appComponent;
  }
}
