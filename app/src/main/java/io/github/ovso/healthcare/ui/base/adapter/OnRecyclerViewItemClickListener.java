package io.github.ovso.healthcare.ui.base.adapter;

import io.github.ovso.healthcare.ui.base.listener.BaseOnItemClickListener;

public interface OnRecyclerViewItemClickListener<T> extends BaseOnItemClickListener<T> {
  void onItemLikeClick(T item);
}