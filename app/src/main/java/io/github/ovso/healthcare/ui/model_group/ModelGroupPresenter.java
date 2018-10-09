package io.github.ovso.healthcare.ui.model_group;

import android.content.Intent;
import android.support.annotation.NonNull;

public interface ModelGroupPresenter {

  void onCreate(@NonNull Intent intent);

  void onDestroy();

  void onListItemClick(Object data);

  interface View {

    void setupRecyclerView();

    void refresh();

    void showBackButton();

    void setTitle(String title);

    void navigateToModel(int id);
  }
}
