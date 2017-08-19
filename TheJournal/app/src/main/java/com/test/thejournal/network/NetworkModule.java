package com.test.thejournal.network;

import com.test.thejournal.application.builder.TheJournalModule;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

  @Provides
  Retrofit provideFeedClient(@Named(TheJournalModule.MAIN_FEED_BASE_URL) String feedUrl) {
    return new Retrofit.Builder().baseUrl(feedUrl)
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }
}
