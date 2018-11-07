package io.github.ovso.healthcare.ui.result;

import android.arch.lifecycle.LifecycleObserver;

public interface ResultPresenter extends LifecycleObserver {

  void onCreate();

  boolean onOptionsItemSelected(int itemId);

  interface View {

    void refresh();

    void setupRecyclerView();

    void setupActionBar();

    void setTitle(String title);

    void navigateToWeb(int itemId, String diseaseName);

    void finish();
  }
}
