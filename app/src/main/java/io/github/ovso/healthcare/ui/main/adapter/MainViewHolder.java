package io.github.ovso.healthcare.ui.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.like.LikeButton;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.network.model.Disease;
import io.github.ovso.healthcare.ui.base.adapter.BaseViewHolder;

public class MainViewHolder extends BaseViewHolder {

  @BindView(R.id.code_text_view) TextView codeTextView;
  @BindView(R.id.disease_text_view) TextView diseaseTextView;
  @BindView(R.id.like_button) LikeButton likeButton;

  private MainViewHolder(View itemView) {
    super(itemView);
  }

  public void bind(Disease disease) {
    super.bind(disease);
    Context context = itemView.getContext();
    codeTextView.setText(disease.getCode());
    diseaseTextView.setText(disease.getName());
  }

  public static MainViewHolder create(ViewGroup parent) {
    return new MainViewHolder(
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.list_item_main, parent, false));
  }

  @OnClick(R.id.root_view) void onItemClick() {
    onRecyclerViewItemClickListener.onListItemClick(itemView, data, itemPosition);
  }
}