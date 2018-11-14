package io.github.ovso.healthcare.ui.result.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.network.model.youtube.SearchItem;
import io.github.ovso.healthcare.ui.base.adapter.BaseViewHolder;
import io.github.ovso.healthcare.ui.base.listener.BaseOnItemClickListener;
import java.util.Date;
import lombok.Setter;
import org.joda.time.DateTime;

public class ResultViewHolder extends BaseViewHolder<SearchItem> {
  @BindView(R.id.title_text_view) TextView titleTextView;
  @BindView(R.id.thumbnail_image_view) AppCompatImageView thumbnailImageView;
  @BindView(R.id.date_text_view) TextView dateTextView;
  @Setter private BaseOnItemClickListener<SearchItem> onItemClickListener;

  private ResultViewHolder(View itemView) {
    super(itemView);
  }

  public void bind(SearchItem item) {
    super.bind(item);
    Glide.with(itemView.getContext())
        .load(item.getSnippet().getThumbnails().getMedium().getUrl())
        .into(thumbnailImageView);
    titleTextView.setText(item.getSnippet().getTitle());
    dateTextView.setText(getDateText(item));
  }

  private String getDateText(SearchItem $item) {
    Date date = $item.getSnippet().getPublishedAt();
    return new DateTime(date).toString("yyyy-MM-dd HH:ss");
  }

  public static ResultViewHolder create(ViewGroup parent) {
    return new ResultViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.list_item_result, parent, false));
  }

  @OnClick(R.id.play_button) void onPlayClick() {
    if (onItemClickListener != null) {
      onItemClickListener.onItemClick(data);
    }
  }
}