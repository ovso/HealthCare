package io.github.ovso.healthcare.ui.main;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import io.github.ovso.healthcare.data.db.model.DiseaseEntity;

public interface MainPresenter extends LifecycleObserver {

  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  void onCreate();

  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
  void reqDatabase();

  void onItemClick(DiseaseEntity disease);

  void changedSearch(CharSequence charSequence);

  void onBackPressed(boolean isDrawerOpen);

  void onItemLikeClick(DiseaseEntity item, boolean checked);

  boolean onNavigationItemSelected(int itemId);

  interface View {

    void setupRecyclerView();

    void refresh();

    void navigateToResult(DiseaseEntity disease);

    void setupToolbar();

    void setupSearchLiveo();

    void closeDrawer();

    void finish();

    void setTitle(String title);

    void showNotiDialog();

    void setupNavigationView();

    void showEmptyAni();

    void hideEmpthAni();

    void navigateToLike();

    void showMessage(String msg);

    void showLicenses();
  }
}
