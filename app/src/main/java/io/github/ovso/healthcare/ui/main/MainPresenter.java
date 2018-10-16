package io.github.ovso.healthcare.ui.main;

import io.github.ovso.healthcare.data.network.model.Disease;

public interface MainPresenter {

  void onCreated();

  void onItemClick(Disease disease);

  void changedSearch(CharSequence charSequence);

  void onBackPressed(boolean isDrawerOpen);

  void onItemLikeClick(Disease item);

  interface View {

    void setupRecyclerView();

    void refresh();

    void navigateToDetail(Disease disease);

    void setupToolbar();

    void setupSearchLiveo();

    void closeDrawer();

    void finish();
  }
}
