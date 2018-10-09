package io.github.ovso.healthcare.ui.base.adapter;

import java.util.List;

public interface BaseAdapterDataModel<T> {
  void add(T item);

  void addAll(List<T> items);

  T remove(int position);

  T getItem(int position);

  void add(int index, T item);

  int getSize();

  void clear();
}
