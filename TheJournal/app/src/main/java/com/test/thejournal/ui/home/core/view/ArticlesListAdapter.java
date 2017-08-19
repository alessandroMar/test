package com.test.thejournal.ui.home.core.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.test.thejournal.R;
import com.test.thejournal.model.PageItems;
import com.test.thejournal.ui.home.core.view.listeners.OnBottomReachListener;
import com.test.thejournal.utils.DateUtil;
import java.util.ArrayList;
import java.util.List;

public class ArticlesListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final int VIEW_TYPE_ITEM = 1;
  private static final int VIEW_TYPE_FOOTER = 2;

  private List<PageItems> articles = new ArrayList<>();
  private OnBottomReachListener onBottomReachListener;

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    if (viewType == VIEW_TYPE_ITEM) {
      View view = inflater.inflate(R.layout.article_item_layout, parent, false);
      return new ArticlesListViewHolder(view);
    } else {
      View view = inflater.inflate(R.layout.articles_footer, parent, false);
      return new LoaderViewHolder(view);
    }
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof ArticlesListViewHolder) {
      ArticlesListViewHolder articlesListViewHolder = (ArticlesListViewHolder) holder;

      PageItems article = articles.get(position);
      if (article.getPrimary_image() != null) {
        articlesListViewHolder.setImage(article.getPrimary_image().getId());
      }

      articlesListViewHolder.setTitle(article.getTitle());
      articlesListViewHolder.setDate(DateUtil.formatDateToPrint(article.getDate()));
      articlesListViewHolder.setHashTag(article.getTags());
    }
    if (position >= getItemCount() - 1) {
      onBottomReachListener.onBottomReached();
    }
  }

  @Override
  public int getItemCount() {
    return articles.size() + 1;
  }

  @Override
  public int getItemViewType(int position) {
    if (position == getItemCount() - 1) {
      return VIEW_TYPE_FOOTER;
    }
    return VIEW_TYPE_ITEM;
  }

  public void addData(List<PageItems> newArticles) {
    this.articles.addAll(newArticles);
    notifyItemInserted(articles.size() - newArticles.size());
  }

  public void setScrollListener(OnBottomReachListener onBottomReachListener) {
    this.onBottomReachListener = onBottomReachListener;
  }

  public void clearData() {
    int itemsToRemove = getItemCount();
    articles.clear();
    notifyItemRangeRemoved(0, itemsToRemove);
  }
}
