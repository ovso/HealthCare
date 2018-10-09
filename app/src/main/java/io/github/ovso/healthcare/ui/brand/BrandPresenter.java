package io.github.ovso.healthcare.ui.brand;

import android.content.Intent;
import android.support.annotation.NonNull;

public interface BrandPresenter {

  void onCreate(@NonNull Intent intent);

  void onDestroy();

  void onListItemClick(Object data);

  interface View {

    void setupRecyclerView();

    void refresh();

    void showBackButton();

    void setTitle(String title);

    void navigateToModelGroup(int id);
  }
}
