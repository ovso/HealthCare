package io.github.ovso.healthcare.ui.like;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public interface LikePresenter extends LifecycleObserver {
  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  void onCreate();

  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  void onDestroy();

  interface View {

    void setupRecyclerView();

    void refresh();
  }
}
