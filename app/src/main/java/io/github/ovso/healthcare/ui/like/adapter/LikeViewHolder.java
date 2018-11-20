package io.github.ovso.healthcare.ui.like.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.lottie.LottieAnimationView;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.db.model.DiseaseEntity;
import io.github.ovso.healthcare.ui.base.adapter.BaseViewHolder;
import io.github.ovso.healthcare.ui.base.listener.DiseaseOnItemClickListener;
import lombok.Setter;

public class LikeViewHolder extends BaseViewHolder<DiseaseEntity> {

  @BindView(R.id.code_text_view) TextView codeTextView;
  @BindView(R.id.disease_text_view) TextView diseaseTextView;
  @BindView(R.id.like_button) LottieAnimationView likeButton;
  @Setter private DiseaseOnItemClickListener<DiseaseEntity> onItemClickListener;

  private LikeViewHolder(View itemView) {
    super(itemView);
  }

  public void bind(DiseaseEntity entity) {
    super.bind(entity);
    codeTextView.setText(entity.code);
    diseaseTextView.setText(entity.name);
    likeButton.setProgress(1);
  }

  public static LikeViewHolder create(ViewGroup parent) {
    return new LikeViewHolder(
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.list_item_disease, parent, false));
  }

  @OnClick(R.id.root_view) void onItemClick() {
    onItemClickListener.onItemClick(data);
  }

  @OnClick(R.id.like_button) void onLikeClick(View view) {
    onItemClickListener.onItemLikeClick(data, false);
  }
}