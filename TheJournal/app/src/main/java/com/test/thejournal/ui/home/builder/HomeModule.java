package com.test.thejournal.ui.home.builder;

import android.app.Activity;
import com.test.thejournal.ui.home.core.interactor.DefaultHomeInteractor;
import com.test.thejournal.ui.home.core.interactor.HomeInteractor;
import com.test.thejournal.ui.home.core.presenter.DefaultHomePresenter;
import com.test.thejournal.ui.home.core.presenter.HomePresenter;
import com.test.thejournal.ui.home.core.view.DefaultHomeView;
import com.test.thejournal.ui.home.core.view.HomeView;
import com.test.thejournal.ui.home.wireframe.DefaultHomeWireframe;
import com.test.thejournal.ui.home.wireframe.HomeWireframe;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class HomeModule {

  private final Activity activity;

  public HomeModule(Activity activity) {
    this.activity = activity;
  }

  @HomeScope
  @Provides
  HomeView provideView() {
    return new DefaultHomeView(activity);
  }

  @HomeScope
  @Provides
  HomePresenter providePresenter(HomeView view, HomeInteractor interactor, HomeWireframe wireframe) {
    return new DefaultHomePresenter(view, interactor, wireframe);
  }

  @HomeScope
  @Provides
  HomeInteractor provideInteractor(Retrofit retrofit) {
    return new DefaultHomeInteractor(retrofit);
  }

  @HomeScope
  @Provides
  HomeWireframe provideWireframe() {
    return new DefaultHomeWireframe(activity);
  }

}
