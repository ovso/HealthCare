package io.github.ovso.healthcare.ui.model;

import android.content.Intent;
import android.support.annotation.NonNull;
import io.github.ovso.healthcare.data.network.model.Car;

public interface ModelPresenter {

  void onCreate(@NonNull Intent intent);

  void onDestroy();

  void onListItemClick(Object data);

  interface View {

    void setupRecyclerView();

    void refresh();

    void showBackButton();

    void setTitle(String title);

    void navigateToModel(int id);

    void navigateToDetail(Car car);

    void navigateToMain(int id);

    void navigateToMain(int id, String name);
  }
}
