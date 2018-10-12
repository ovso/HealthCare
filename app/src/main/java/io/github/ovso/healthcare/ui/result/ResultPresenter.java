package io.github.ovso.healthcare.ui.result;

import android.content.Intent;

public interface ResultPresenter {

  void onCreate(Intent intent);

  interface View {

    void refresh();

    void setupRecyclerView();
  }
}
