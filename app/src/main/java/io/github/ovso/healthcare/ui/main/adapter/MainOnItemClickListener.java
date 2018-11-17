package io.github.ovso.healthcare.ui.main.adapter;

import io.github.ovso.healthcare.ui.base.listener.BaseOnItemClickListener;

public interface MainOnItemClickListener<T> extends BaseOnItemClickListener<T> {
  void onItemLikeClick(T item, boolean checked);
}