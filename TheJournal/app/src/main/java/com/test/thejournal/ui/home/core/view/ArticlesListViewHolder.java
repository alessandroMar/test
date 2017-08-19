package com.test.thejournal.ui.home.core.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.test.thejournal.R;
import com.test.thejournal.model.Tag;
import java.util.List;

class ArticlesListViewHolder extends RecyclerView.ViewHolder {

  private final ImageView image;
  private final TextView title;
  private final TextView date;
  private final TextView hashTag;

  public ArticlesListViewHolder(View view) {
    super(view);
    image = (ImageView) view.findViewById(R.id.article_image);
    title = (TextView) view.findViewById(R.id.article_title);
    date = (TextView) view.findViewById(R.id.article_date);
    hashTag = (TextView) view.findViewById(R.id.article_hashtag);
  }

  public void setTitle(String titleString) {
    String title;
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
      title = Html.fromHtml(titleString, Html.FROM_HTML_MODE_LEGACY).toString();
    } else {
      title = Html.fromHtml(titleString).toString();
    }
    this.title.setText(title);
  }

  public void setDate(String date) {
    this.date.setText(date);
  }

  public void setImage(String id) {
    Context context = image.getContext();
    String url = context.getString(R.string.images_format_url);
    url = String.format(url, id, 301);  //Improvement: calculate width based on device's density
    Picasso.with(context).load(url).into(image);
  }

  public void setHashTag(List<Tag> tags) {
    if (tags != null && !tags.isEmpty()) {
      hashTag.setText("#" + tags.get(0).getSlug());
    }
  }
}
