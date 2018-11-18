package io.github.ovso.healthcare.ui.like.adapter;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import io.github.ovso.healthcare.data.db.model.DiseaseEntity;
import io.github.ovso.healthcare.ui.base.IBuilder;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.base.adapter.BaseViewHolder;
import io.github.ovso.healthcare.ui.base.listener.DiseaseOnItemClickListener;
import java.util.ArrayList;
import java.util.List;
import lombok.Setter;
import lombok.experimental.Accessors;

public class LikeAdapter extends RecyclerView.Adapter<BaseViewHolder> implements BaseAdapterView,
    BaseAdapterDataModel<DiseaseEntity> {
  private DiseaseOnItemClickListener onItemClickListener;
  private List<DiseaseEntity> items = new ArrayList<>();

  private LikeAdapter(Builder builder) {
    onItemClickListener = builder.itemClickListener;
  }

  @NonNull @Override
  public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
    return LikeViewHolder.create(viewGroup);
  }

  @Override public void onBindViewHolder(@NonNull BaseViewHolder baseViewHolder, int position) {
    if (baseViewHolder instanceof LikeViewHolder) {
      LikeViewHolder viewHolder = (LikeViewHolder) baseViewHolder;
      viewHolder.bind(getItem(position));
      viewHolder.setOnItemClickListener(onItemClickListener);
    }
  }

  @Override public int getItemCount() {
    return getSize();
  }

  @Override public void add(DiseaseEntity item) {
    items.add(item);
  }

  @Override public void addAll(List<DiseaseEntity> $items) {
    items.addAll($items);
  }

  @Override public DiseaseEntity remove(int position) {
    return items.remove(position);
  }

  @Override public DiseaseEntity getItem(int position) {
    return items.get(position);
  }

  @Override public void add(int index, DiseaseEntity item) {
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

  public static class Builder implements IBuilder<LikeAdapter> {
    @Setter @Accessors(chain = true) private DiseaseOnItemClickListener itemClickListener;

    @Override public LikeAdapter build() {
      return new LikeAdapter(this);
    }
  }
}