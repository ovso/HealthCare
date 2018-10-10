package io.github.ovso.healthcare.ui.main;

import io.github.ovso.healthcare.data.network.model.Disease;

public interface MainPresenter {

  void onCreated();

  void onListItemClick(Disease disease, int itemPosition);

  void changedSearch(CharSequence charSequence);

  interface View {

    void setupRecyclerView();

    void refresh();

    void navigateToDetail(Disease disease);

    void setupToolbar();

    void setupSearchLiveo();
  }
}
