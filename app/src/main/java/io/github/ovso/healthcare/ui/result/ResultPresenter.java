package io.github.ovso.healthcare.ui.result;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import io.github.ovso.healthcare.data.network.model.youtube.SearchItem;

public interface ResultPresenter extends LifecycleObserver {
  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  void onCreate();

  boolean onOptionsItemSelected(int itemId);

  void onItemClick(SearchItem item);

  void onSwipeRefresh();

  void onLoadMore();

  interface View {

    void refresh();

    void setupRecyclerView();

    void setupActionBar();

    void setTitle(String title);

    void navigateToWeb(int itemId, String diseaseName);

    void finish();

    void navigateToVideo(SearchItem item);

    void setupSwipeRefresh();

    void showLoading();

    void hideLoading();

    void setLoaded();
  }
}
