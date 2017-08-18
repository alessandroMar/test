package com.test.thejournal.ui.home.core.interactor;

import com.test.thejournal.model.FeedResponse;
import com.test.thejournal.network.TheJournalApi;
import io.reactivex.Observable;
import retrofit2.Retrofit;

public class DefaultHomeInteractor implements HomeInteractor {

  private Retrofit retrofit;

  public DefaultHomeInteractor(Retrofit retrofit) {
    this.retrofit = retrofit;
  }

  @Override
  public Observable<FeedResponse> getNews(String publication, int page) {
    return retrofit.create(TheJournalApi.class).getNews(publication, page);
  }
}
