package io.github.ovso.healthcare.ui.like;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import io.github.ovso.healthcare.data.db.model.DiseaseEntity;

public interface LikePresenter extends LifecycleObserver {
  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  void onCreate();

  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  void onDestroy();

  void onItemLikeClick(DiseaseEntity item, boolean checked);

  void onItemClick(DiseaseEntity item);

  interface View {

    void setupRecyclerView();

    void refresh();

    void navigateToResult(DiseaseEntity item);

    void setupActionBar();

    void showEmptyAni();

    void hideEmpthAni();

    void hideRecyclerView();

    void showRecyclerView();
  }
}
