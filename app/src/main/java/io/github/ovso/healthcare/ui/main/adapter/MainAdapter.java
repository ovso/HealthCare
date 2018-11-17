package io.github.ovso.healthcare.ui.main.adapter;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.ovso.healthcare.data.network.model.Disease;
import io.github.ovso.healthcare.ui.base.IBuilder;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.base.adapter.BaseViewHolder;
import java.util.ArrayList;
import java.util.List;
import lombok.Setter;
import lombok.experimental.Accessors;

public class MainAdapter extends RecyclerView.Adapter<BaseViewHolder> implements BaseAdapterView,
    BaseAdapterDataModel<Disease> {
  private MainOnItemClickListener onItemClickListener;
  private List<Disease> items = new ArrayList<>();

  private MainAdapter(Builder builder) {
    onItemClickListener = builder.itemClickListener;
  }

  @NonNull @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
    return MainViewHolder.create(viewGroup);
  }

  @Override public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int position) {
    if (baseViewHolder instanceof MainViewHolder) {
      MainViewHolder viewHolder = (MainViewHolder) baseViewHolder;
      viewHolder.bind(items.get(position));
      viewHolder.setOnItemClickListener(onItemClickListener);
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

  public static class Builder implements IBuilder<MainAdapter> {
    @Setter @Accessors(chain = true) private MainOnItemClickListener itemClickListener;

    @Override public MainAdapter build() {
      return new MainAdapter(this);
    }
  }
}