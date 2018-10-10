package io.github.ovso.healthcare.ui.main;

public interface MainPresenter {

  void onCreated();

  void onListItemClick(Object data, int itemPosition);

  void changedSearch(CharSequence charSequence);

  interface View {

    void setupRecyclerView();

    void refresh();

    void navigateToDetail(Object data);

    void setupToolbar();

    void setupSearchLiveo();
  }
}
