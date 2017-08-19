package com.test.thejournal.ui.home.core.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.test.thejournal.R;
import com.test.thejournal.model.PageItems;
import com.test.thejournal.ui.home.core.view.listeners.OnSubscriberRefreshListener;
import com.test.thejournal.ui.home.core.view.listeners.OnSubscriberScrollListener;
import java.util.List;
import rx.Observable;
import rx.Observer;

public class DefaultHomeView extends LinearLayout implements HomeView {

  private final Observer<Boolean> observer = new Observer<Boolean>() {
    @Override
    public void onCompleted() {
      if (swipeRefreshLayout.isRefreshing())
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(Boolean refreshed) {

    }
  };

  private ProgressBar progressBar;
  private SwipeRefreshLayout swipeRefreshLayout;
  private RecyclerView recyclerView;
  private View emptyState;
  private final ArticlesListAdapter adapter;

  public DefaultHomeView(Context context) {
    super(context);
    inflate(context, R.layout.home_view, this);

    progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh_view);
    //swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
    //  @Override
    //  public void onRefresh() {
    //    onRefreshListener.onRefresh();
    //  }
    //});
    recyclerView = (RecyclerView) findViewById(R.id.feeds_list);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    adapter = new ArticlesListAdapter();
    recyclerView.setAdapter(adapter);

    emptyState = findViewById(R.id.empty_state_view);
  }

  @Override
  public void addNewsToList(List<PageItems> articles) {
    adapter.addData(articles);
  }

  @Override
  public Observable<Void> observeRefresh() {
    return Observable.create(new OnSubscriberRefreshListener(swipeRefreshLayout));
  }

  @Override
  public Observable<Boolean> observeScroll() {
    return Observable.create(new OnSubscriberScrollListener(adapter::setScrollListener));
  }

  @Override
  public void showLoading() {
    progressBar.setVisibility(VISIBLE);
  }

  @Override
  public void hideLoading() {
    progressBar.setVisibility(GONE);
  }

  @Override
  public void showNews() {
    recyclerView.setVisibility(VISIBLE);
  }

  @Override
  public void hideNews() {
    recyclerView.setVisibility(GONE);
  }

  @Override
  public void showEmptyState() {
    emptyState.setVisibility(VISIBLE);
  }

  @Override
  public void hideEmptyState() {
    emptyState.setVisibility(GONE);
  }

  @Override
  public void hideRefreshLoading() {
    swipeRefreshLayout.setRefreshing(false);
  }

  @Override
  public void clearNews() {
    adapter.clearData( );
  }

  @Override
  public View getView() {
    return this;
  }
}
