package io.github.ovso.healthcare.ui.like;

import android.content.Intent;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import com.fondesa.recyclerviewdivider.RecyclerViewDivider;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.KeyName;
import io.github.ovso.healthcare.data.db.model.DiseaseEntity;
import io.github.ovso.healthcare.ui.base.BaseActivity;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.base.listener.DiseaseOnItemClickListener;
import io.github.ovso.healthcare.ui.like.adapter.LikeAdapter;
import io.github.ovso.healthcare.ui.result.ResultActivity;
import javax.inject.Inject;

public class LikeActivity extends BaseActivity implements LikePresenter.View,
    DiseaseOnItemClickListener<DiseaseEntity> {

  @BindView(R.id.recycler_view) RecyclerView recyclerView;
  @Inject LikePresenter presenter;
  @Inject LikeAdapter adapter;
  @Inject BaseAdapterView adapterView;

  @Override protected int getLayoutResID() {
    return R.layout.activity_like;
  }

  @Override public void setupRecyclerView() {
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(adapter);
    RecyclerViewDivider.with(this)
        .size(1)
        .color(ContextCompat.getColor(this, android.R.color.darker_gray))
        .build()
        .addTo(recyclerView);
  }

  @Override public void refresh() {
    adapterView.refresh();
  }

  @Override public void navigateToResult(DiseaseEntity item) {
    Intent intent = new Intent(this, ResultActivity.class);
    intent.putExtra(KeyName.DISEASE_NAME.getValue(), item.name);
    startActivity(intent);

  }

  @Override public void onItemLikeClick(DiseaseEntity item, boolean checked) {
    presenter.onItemLikeClick(item, checked);
  }

  @Override public void onItemClick(DiseaseEntity entity) {
    presenter.onItemClick(entity);
  }
}
