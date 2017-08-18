package com.test.thejournal.application.builder;

import android.app.Application;
import android.content.Context;
import com.test.thejournal.R;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

@Module
public class TheJournalModule {

  public static final String MAIN_FEED_BASE_URL = "main_feed_base_url";
  private final Application application;

  public TheJournalModule(Application application) {
    this.application = application;
  }

  @Provides
  @TheJournalAppScope
  Context provideContext() {
    return application.getApplicationContext();
  }

  @Provides
  @TheJournalAppScope
  @Named(MAIN_FEED_BASE_URL)
  String provideFeedUrl(Context context) {
    return context.getString(R.string.main_feed_base_url);
  }
}
