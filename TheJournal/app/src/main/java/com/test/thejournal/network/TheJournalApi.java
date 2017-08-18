package com.test.thejournal.network;

import com.test.thejournal.model.FeedResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheJournalApi {

  @GET("/v3/river/{publication}")
  Observable<FeedResponse> getNews(@Path("publication") String path, @Query("page") int page);
}
