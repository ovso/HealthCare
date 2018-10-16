package io.github.ovso.healthcare.ui.base.adapter;

public interface OnRecyclerViewItemClickListener<T> {
  void onItemClick(T item);

  void onItemLikeClick(T item);
}