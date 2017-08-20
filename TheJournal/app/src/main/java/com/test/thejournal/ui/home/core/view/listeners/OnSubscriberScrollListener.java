package com.test.thejournal.ui.home.core.view.listeners;

import rx.Observable;
import rx.Subscriber;
import rx.android.MainThreadSubscription;
import rx.functions.Action1;

public class OnSubscriberScrollListener implements Observable.OnSubscribe<Void> {

  private Action1<OnBottomReachListener> bindingFunction;

  public OnSubscriberScrollListener(Action1<OnBottomReachListener> bindingFunction) {
    this.bindingFunction = bindingFunction;
  }

  @Override
  public void call(Subscriber<? super Void> subscriber) {

    final OnBottomReachListener listener = () -> subscriber.onNext(null);
    bindingFunction.call(listener);

    subscriber.add(new MainThreadSubscription() {
      @Override
      protected void onUnsubscribe() {
        bindingFunction.call(null);
      }
    });
  }
}
