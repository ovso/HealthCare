package io.github.ovso.healthcare.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.like.LikeButton;
import com.like.OnLikeListener;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.db.model.DiseaseEntity;
import io.github.ovso.healthcare.ui.base.adapter.BaseViewHolder;
import lombok.Setter;

public class MainViewHolder extends BaseViewHolder<DiseaseEntity> implements OnLikeListener {

  @BindView(R.id.code_text_view) TextView codeTextView;
  @BindView(R.id.disease_text_view) TextView diseaseTextView;
  @BindView(R.id.like_button) LikeButton likeButton;
  @Setter private MainOnItemClickListener<DiseaseEntity> onItemClickListener;

  private MainViewHolder(View itemView) {
    super(itemView);
  }

  public void bind(DiseaseEntity entity) {
    super.bind(entity);
    codeTextView.setText(entity.code);
    diseaseTextView.setText(entity.name);
    likeButton.setOnLikeListener(this);
    likeButton.setLiked(entity.like);
  }

  public static MainViewHolder create(ViewGroup parent) {
    return new MainViewHolder(
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.list_item_main, parent, false));
  }

  @OnClick(R.id.root_view) void onItemClick() {
    onItemClickListener.onItemClick(data);
  }

  @Override public void liked(LikeButton likeButton) {
    onItemClickListener.onItemLikeClick(data, true);
  }

  @Override public void unLiked(LikeButton likeButton) {
    onItemClickListener.onItemLikeClick(data, false);
  }
}