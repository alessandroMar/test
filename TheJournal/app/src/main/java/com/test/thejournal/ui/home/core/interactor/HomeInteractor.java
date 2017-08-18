package com.test.thejournal.ui.home.core.interactor;

import com.test.thejournal.model.FeedResponse;
import io.reactivex.Observable;

public interface HomeInteractor {

  Observable<FeedResponse> getNews(String publication, int page);
}
