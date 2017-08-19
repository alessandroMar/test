package com.test.thejournal.ui.home.core.presenter;

import com.test.thejournal.BuildConfig;
import com.test.thejournal.model.PageItems;
import com.test.thejournal.ui.home.core.interactor.HomeInteractor;
import com.test.thejournal.ui.home.core.view.HomeView;
import com.test.thejournal.ui.home.wireframe.HomeWireframe;
import java.util.List;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class DefaultHomePresenter implements HomePresenter {

  private static final String POST_TYPE = "post";
  private HomeView view;
  private HomeInteractor interactor;
  private HomeWireframe wireframe;

  private CompositeSubscription subscription = new CompositeSubscription();
  private int lastPageLoaded = 1;

  public DefaultHomePresenter(HomeView view, HomeInteractor interactor, HomeWireframe wireframe) {
    this.view = view;
    this.interactor = interactor;
    this.wireframe = wireframe;
  }

  @Override
  public void create() {
    subscription.add(observeScroll());
    subscription.add(observeRefresh());
    subscription.add(loadFirstNews());
    //todo add empty state action try again
  }

  private Subscription loadFirstNews() {
    return getObservableNews(1).observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .doOnSubscribe(() -> view.showLoading())
        .subscribe(this::showNews, this::handleError);
  }

  private Observable<List<PageItems>> getObservableNews(int pageToLoad) {
    return interactor.getNews(BuildConfig.publication, pageToLoad)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .flatMap(feedResponse -> Observable.from(feedResponse.getResponse().getPage_items())
            .filter(pageItems -> POST_TYPE.equalsIgnoreCase(pageItems.getType())))
        .toList();
  }

  private Subscription observeRefresh() {
    return view.observeRefresh().subscribe((__) -> refreshNews());
  }

  private void refreshNews() {
    getObservableNews(1).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(this::showRefreshedNews, this::handleRefreshError);
  }

  private void handleRefreshError(Throwable throwable) {
    view.hideRefreshLoading();
  }

  private void showRefreshedNews(List<PageItems> pageItems) {
    lastPageLoaded = 1;
    view.hideEmptyState();
    view.showNews();
    view.clearNews();
    view.showNews();
    view.addNewsToList(pageItems);
    view.hideRefreshLoading();
  }

  private Subscription observeScroll() {
    return view.observeScroll().subscribe(bottomReached -> getMoreNews());
  }

  private void getMoreNews() {
    getObservableNews(++lastPageLoaded).subscribe(this::showMoreNews);
  }

  private void handleError(Throwable throwable) {
    throwable.printStackTrace();
    view.hideLoading();
    view.showEmptyState();
  }

  private void showNews(List<PageItems> pageItems) {
    view.hideLoading();
    view.showNews();
    view.addNewsToList(pageItems);
  }

  private void showMoreNews(List<PageItems> pageItems) {
    view.addNewsToList(pageItems);
  }

  @Override
  public void destroy() {
    subscription.clear();
  }
}
