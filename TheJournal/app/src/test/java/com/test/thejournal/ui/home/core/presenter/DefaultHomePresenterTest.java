package com.test.thejournal.ui.home.core.presenter;

import com.test.thejournal.BuildConfig;
import com.test.thejournal.model.FeedResponse;
import com.test.thejournal.model.PageItems;
import com.test.thejournal.model.Response;
import com.test.thejournal.ui.home.core.interactor.HomeInteractor;
import com.test.thejournal.ui.home.core.view.HomeView;
import com.test.thejournal.ui.home.wireframe.HomeWireframe;
import com.twistedequations.mvl.rx.TestAndroidRxSchedulers;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DefaultHomePresenterTest {

  private DefaultHomePresenter presenter;

  @Mock
  private HomeView view;
  @Mock
  private HomeInteractor interactor;
  @Mock
  private HomeWireframe wireframe;

  @Before
  public void setup() {
    RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
      @Override
      public Scheduler getMainThreadScheduler() {
        return Schedulers.immediate();
      }
    });
    MockitoAnnotations.initMocks(this);
    presenter = new DefaultHomePresenter(view, interactor, wireframe, new TestAndroidRxSchedulers());

    when(view.observeScroll()).thenReturn(Observable.never());
    when(view.observeRefresh()).thenReturn(Observable.never());
  }

  @After
  public void tearDown() {
    RxAndroidPlugins.getInstance().reset();
  }

  @Test
  public void should_createTest_handleError() {
    //given
    when(interactor.getNews(BuildConfig.publication, 1)).thenReturn(Observable.error(new RuntimeException("fail")));

    //when
    presenter.create();
    //then

    verify(view).showLoading();
    verify(view).hideLoading();
    verify(view).showEmptyState();
  }

  @Test
  public void should_createTest_showNews() {
    //given
    FeedResponse feedResponse = new FeedResponse();
    Response response = new Response();
    List<PageItems> page_items = new ArrayList<>();
    response.setPage_items(page_items);
    feedResponse.setResponse(response);
    when(interactor.getNews(BuildConfig.publication, 1)).thenReturn(Observable.just(feedResponse));

    //when
    presenter.create();

    //then
    verify(view).showLoading();
    verify(view).hideLoading();
    verify(view).showNews();
    verify(view).addNewsToList(feedResponse.getResponse().getPage_items());
  }

  @Test
  public void should_refreshNews_refreshNews() {
    //given
    FeedResponse feedResponse = new FeedResponse();
    Response response = new Response();
    List<PageItems> page_items = new ArrayList<>();
    response.setPage_items(page_items);
    feedResponse.setResponse(response);
    when(interactor.getNews(BuildConfig.publication, 1)).thenReturn(Observable.just(feedResponse));
    when(view.observeRefresh()).thenReturn(Observable.just(null));

    //when
    presenter.create();

    //then
    verify(view).hideEmptyState();
    verify(view).clearNews();
    verify(view, times(2)).showNews();
    verify(view, times(2)).addNewsToList(page_items);
    verify(view).hideRefreshLoading();
  }


}