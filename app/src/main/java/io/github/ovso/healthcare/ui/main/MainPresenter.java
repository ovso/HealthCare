package io.github.ovso.healthcare.ui.main;

import io.github.ovso.healthcare.data.network.model.Disease;

public interface MainPresenter {

  void onCreated();

  void onListItemClick(Disease disease, int itemPosition);

  void changedSearch(CharSequence charSequence);

  void onBackPressed(boolean isDrawerOpen);

  interface View {

    void setupRecyclerView();

    void refresh();

    void navigateToDetail(String name);

    void setupToolbar();

    void setupSearchLiveo();

    void closeDrawer();

    void finish();
  }
}
