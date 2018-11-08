package io.github.ovso.healthcare.ui.main.adapter;

import android.support.annotation.NonNull;
import android.view.ViewGroup;
import io.github.ovso.healthcare.data.network.model.Disease;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.base.adapter.BaseRecyclerAdapter;
import io.github.ovso.healthcare.ui.base.adapter.BaseViewHolder;
import io.github.ovso.healthcare.ui.base.adapter.OnRecyclerViewItemClickListener;
import java.util.ArrayList;
import java.util.List;
import lombok.Setter;

public class MainAdapter extends BaseRecyclerAdapter implements BaseAdapterView,
    BaseAdapterDataModel<Disease> {
  @Setter private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
  private List<Disease> items = new ArrayList<>();

  @NonNull @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
    return MainViewHolder.create(viewGroup);
  }

  @Override public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int position) {
    if (baseViewHolder instanceof MainViewHolder) {
      MainViewHolder viewHolder = (MainViewHolder) baseViewHolder;
      viewHolder.bind(items.get(position));
      viewHolder.setOnRecyclerViewItemClickListener(onRecyclerViewItemClickListener);
    }
  }

  @Override public int getItemCount() {
    return getSize();
  }

  @Override public void add(Disease item) {
    items.add(item);
  }

  @Override public void addAll(List<Disease> $items) {
    items.addAll($items);
  }

  @Override public Disease remove(int position) {
    return items.remove(position);
  }

  @Override public Disease getItem(int position) {
    return items.get(position);
  }

  @Override public void add(int index, Disease item) {
    items.add(index, item);
  }

  @Override public int getSize() {
    return items.size();
  }

  @Override public void clear() {
    items.clear();
  }

  @Override public void refresh() {
    notifyDataSetChanged();
  }
}