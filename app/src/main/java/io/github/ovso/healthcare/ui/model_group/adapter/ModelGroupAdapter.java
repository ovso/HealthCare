package io.github.ovso.healthcare.ui.model_group.adapter;

import android.view.ViewGroup;
import io.github.ovso.healthcare.data.network.model.ModelGroup;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.base.adapter.BaseRecyclerAdapter;
import io.github.ovso.healthcare.ui.base.adapter.BaseViewHolder;
import io.github.ovso.healthcare.ui.base.adapter.OnRecyclerViewItemClickListener;
import java.util.ArrayList;
import java.util.List;
import lombok.Setter;

public class ModelGroupAdapter extends BaseRecyclerAdapter implements BaseAdapterView,
    BaseAdapterDataModel<ModelGroup> {
  @Setter private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
  private List<ModelGroup> items = new ArrayList<>();

  @Override public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return ModelGroupViewHolder.create(parent);
  }

  @Override
  public void onBindViewHolder(BaseViewHolder viewHolder, int position) {
    if (viewHolder instanceof ModelGroupViewHolder) {
      ModelGroupViewHolder holder = (ModelGroupViewHolder) viewHolder;
      holder.bind(items.get(position));
      holder.setOnRecyclerViewItemClickListener(onRecyclerViewItemClickListener);
      holder.setItemPosition(position);
    }
  }

  @Override public int getItemCount() {
    return getSize();
  }

  @Override public void add(ModelGroup item) {
    items.add(item);
  }

  @Override public void addAll(List<ModelGroup> $items) {
    items.addAll($items);
  }

  @Override public ModelGroup remove(int position) {
    return items.remove(position);
  }

  @Override public ModelGroup getItem(int position) {
    return items.get(position);
  }

  @Override public void add(int index, ModelGroup item) {
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