package com.test.thejournal.ui.home.core.view;

import com.test.thejournal.model.PageItems;
import com.test.thejournal.ui.common.BaseView;
import java.util.List;
import rx.Observable;

public interface HomeView extends BaseView {

  void addNewsToList(List<PageItems> page_items);

  Observable<Void> observeRefresh();

  Observable<Boolean> observeScroll();

  void clearNews();

  void hideRefreshLoading();
}
