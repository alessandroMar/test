package com.test.thejournal.ui.home.core.presenter;

import android.util.Log;
import com.test.thejournal.model.FeedResponse;
import com.test.thejournal.ui.home.core.interactor.HomeInteractor;
import com.test.thejournal.ui.home.core.view.HomeView;
import com.test.thejournal.ui.home.wireframe.HomeWireframe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DefaultHomePresenter implements HomePresenter {

  private HomeView view;
  private HomeInteractor interactor;
  private HomeWireframe wireframe;

  public DefaultHomePresenter(HomeView view, HomeInteractor interactor, HomeWireframe wireframe) {
    this.view = view;
    this.interactor = interactor;
    this.wireframe = wireframe;
  }

  @Override
  public void create() {
    interactor.getNews("thejournal", 1) //todo
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(this::showNews, this::handleError);
  }

  private void handleError(Throwable throwable) {
    throwable.printStackTrace();
  }

  private void showNews(FeedResponse feedResponse) {
    Log.d("test_", feedResponse.getResponse().getPage_items().get(0).getTitle());
  }

  @Override
  public void destroy() {

  }
}
