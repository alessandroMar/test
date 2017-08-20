package com.test.thejournal.application.builder;

import com.twistedequations.mvl.rx.AndroidRxSchedulers;
import com.twistedequations.mvl.rx.DefaultAndroidRxSchedulers;
import dagger.Module;
import dagger.Provides;

@Module
public class RxModule {

  @Provides
  @TheJournalAppScope
  public AndroidRxSchedulers androidRxSchedulers() {
    return new DefaultAndroidRxSchedulers();
  }
}
