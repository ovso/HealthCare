package io.github.ovso.healthcare.ui.like;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import io.github.ovso.healthcare.data.db.AppDatabase;
import io.github.ovso.healthcare.data.db.model.DiseaseEntity;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.utils.ResourceProvider;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

public class LikePresenterImpl implements LikePresenter, Observer<List<DiseaseEntity>> {

  private LikePresenter.View view;
  private AppDatabase database;
  private ResourceProvider resProvider;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private BaseAdapterDataModel<DiseaseEntity> adapterDataModel;
  private LifecycleOwner lifecycleOwner;

  public LikePresenterImpl(LikePresenter.View $view, AppDatabase $database,
      ResourceProvider $resProvider, BaseAdapterDataModel<DiseaseEntity> $adapterDataModel,
      LifecycleOwner $lifecycleOwner) {
    view = $view;
    database = $database;
    resProvider = $resProvider;
    adapterDataModel = $adapterDataModel;
    lifecycleOwner = $lifecycleOwner;
  }

  @Override public void onCreate() {
    view.setupRecyclerView();
    database.diseaseDao().getLikeLiveItems().observe(lifecycleOwner, this);
  }

  @Override public void onDestroy() {
    compositeDisposable.clear();
  }

  @Override public void onItemLikeClick(DiseaseEntity item, boolean checked) {
    item.like = checked;
    database.diseaseDao().update(item);
  }

  @Override public void onItemClick(DiseaseEntity item) {
    view.navigateToResult(item);
  }

  @Override public void onChanged(List<DiseaseEntity> entities) {
    adapterDataModel.clear();
    adapterDataModel.addAll(entities);
    view.refresh();
  }
}