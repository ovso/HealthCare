package io.github.ovso.healthcare.ui.model.adapter;

import android.view.ViewGroup;
import io.github.ovso.healthcare.data.network.model.Model;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.base.adapter.BaseRecyclerAdapter;
import io.github.ovso.healthcare.ui.base.adapter.BaseViewHolder;
import io.github.ovso.healthcare.ui.base.adapter.OnRecyclerViewItemClickListener;
import java.util.ArrayList;
import java.util.List;
import lombok.Setter;

public class ModelAdapter extends BaseRecyclerAdapter implements BaseAdapterView,
    BaseAdapterDataModel<Model> {
  @Setter private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
  private List<Model> items = new ArrayList<>();

  @Override public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return ModelViewHolder.create(parent);
  }

  @Override
  public void onBindViewHolder(BaseViewHolder viewHolder, int position) {
    if (viewHolder instanceof ModelViewHolder) {
      ModelViewHolder holder = (ModelViewHolder) viewHolder;
      holder.bind(items.get(position));
      holder.setOnRecyclerViewItemClickListener(onRecyclerViewItemClickListener);
      holder.setItemPosition(position);
    }
  }

  @Override public int getItemCount() {
    return getSize();
  }

  @Override public void add(Model item) {
    items.add(item);
  }

  @Override public void addAll(List<Model> $items) {
    items.addAll($items);
  }

  @Override public Model remove(int position) {
    return items.remove(position);
  }

  @Override public Model getItem(int position) {
    return items.get(position);
  }

  @Override public void add(int index, Model item) {
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