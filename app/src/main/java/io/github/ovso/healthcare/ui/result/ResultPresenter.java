package io.github.ovso.healthcare.ui.result;

import android.arch.lifecycle.LifecycleObserver;

public interface ResultPresenter extends LifecycleObserver {

  void onCreate();

  interface View {

    void refresh();

    void setupRecyclerView();

    void setupActionBar();

    void setTitle(String title);
  }
}
