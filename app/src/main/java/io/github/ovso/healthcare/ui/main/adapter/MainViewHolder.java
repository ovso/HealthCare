package io.github.ovso.healthcare.ui.main.adapter;

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

public class MainViewHolder extends BaseViewHolder<DiseaseEntity> {

  @BindView(R.id.code_text_view) TextView codeTextView;
  @BindView(R.id.disease_text_view) TextView diseaseTextView;
  @BindView(R.id.like_button) LottieAnimationView likeButton;
  @Setter private DiseaseOnItemClickListener<DiseaseEntity> onItemClickListener;

  private MainViewHolder(View itemView) {
    super(itemView);
  }

  public void bind(DiseaseEntity entity) {
    super.bind(entity);
    codeTextView.setText(entity.code);
    diseaseTextView.setText(entity.name);
    likeButton.setTag(entity.like);
    likeButton.setProgress(entity.like ? 1 : 0);
  }

  @OnClick(R.id.like_button) void onLikeClick(View view) {
    final boolean check = !((boolean) view.getTag());
    if (check) {
      likeButton.playAnimation();
    } else {
      likeButton.cancelAnimation();
      likeButton.setProgress(0);
    }

    likeButton.setTag(check);
    onItemClickListener.onItemLikeClick(data, check);
  }

  public static MainViewHolder create(ViewGroup parent) {
    return new MainViewHolder(
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.list_item_disease, parent, false));
  }

  @OnClick(R.id.root_view) void onItemClick() {
    onItemClickListener.onItemClick(data);
  }
}