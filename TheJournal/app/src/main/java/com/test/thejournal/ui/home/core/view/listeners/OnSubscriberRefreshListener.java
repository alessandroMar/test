package com.test.thejournal.ui.home.core.view.listeners;

import android.support.v4.widget.SwipeRefreshLayout;
import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;

public class OnSubscriberRefreshListener implements Observable.OnSubscribe<Void> {

  private final SwipeRefreshLayout view;

  public OnSubscriberRefreshListener(SwipeRefreshLayout view) {
    this.view = view;
  }

  @Override
  public void call(Subscriber<? super Void> subscriber) {

    final SwipeRefreshLayout.OnRefreshListener listener = () -> subscriber.onNext(null);
    view.setOnRefreshListener(listener);

    subscriber.add(new MainThreadSubscription() {
      @Override
      protected void onUnsubscribe() {
        view.setOnRefreshListener(null);
      }
    });
  }
}