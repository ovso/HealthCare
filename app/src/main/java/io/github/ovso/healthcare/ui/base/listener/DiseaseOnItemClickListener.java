package io.github.ovso.healthcare.ui.base.listener;

public interface DiseaseOnItemClickListener<T> extends BaseOnItemClickListener<T> {
  void onItemLikeClick(T item, boolean checked);
}