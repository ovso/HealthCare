package io.github.ovso.healthcare.ui.result.adapter;

import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.network.model.youtube.SearchItem;
import io.github.ovso.healthcare.ui.base.adapter.BaseViewHolder;

public class ResultViewHolder extends BaseViewHolder<SearchItem> {
  @BindView(R.id.title_text_view) TextView titleTextView;
  @BindView(R.id.thumbnail_image_view) AppCompatImageView thumbnailImageView;

  private ResultViewHolder(View itemView) {
    super(itemView);
  }

  public void bind(SearchItem item) {
    super.bind(item);
    Glide.with(itemView.getContext())
        .load(item.getSnippet().getThumbnails().getMedium().getUrl())
        .into(thumbnailImageView);
  }

  public static ResultViewHolder create(ViewGroup parent) {
    return new ResultViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item_result, parent, false));
  }

  @OnClick(R.id.root_view) void onItemClick() {
    onRecyclerViewItemClickListener.onListItemClick(itemView, data, itemPosition);
  }
}