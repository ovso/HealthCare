package io.github.ovso.healthcare.ui.result.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import io.github.ovso.healthcare.data.network.model.youtube.SearchItem;
import io.github.ovso.healthcare.ui.base.IBuilder;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterDataModel;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.base.adapter.BaseViewHolder;
import io.github.ovso.healthcare.ui.base.listener.BaseOnItemClickListener;
import java.util.ArrayList;
import java.util.List;
import lombok.Setter;
import lombok.experimental.Accessors;

public class ResultAdapter extends RecyclerView.Adapter<BaseViewHolder>
    implements BaseAdapterView, BaseAdapterDataModel<SearchItem> {
  private BaseOnItemClickListener<SearchItem> onItemClickListener;

  private ResultAdapter(Builder builder) {
    onItemClickListener = builder.clickListener;
  }

  private List<SearchItem> items = new ArrayList<>();

  @Override public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return ResultViewHolder.create(parent);
  }

  @Override
  public void onBindViewHolder(BaseViewHolder viewHolder, int position) {
    if (viewHolder instanceof ResultViewHolder) {
      ResultViewHolder holder = (ResultViewHolder) viewHolder;
      holder.bind(items.get(position));
      holder.setOnItemClickListener(onItemClickListener);
    }
  }

  @Override public int getItemCount() {
    return getSize();
  }

  @Override public void add(SearchItem item) {
    items.add(item);
  }

  @Override public void addAll(List<SearchItem> $items) {
    items.addAll($items);
  }

  @Override public SearchItem remove(int position) {
    return items.remove(position);
  }

  @Override public SearchItem getItem(int position) {
    return items.get(position);
  }

  @Override public void add(int index, SearchItem item) {
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

  public static class Builder implements IBuilder<ResultAdapter> {
    @Setter @Accessors(chain = true) private BaseOnItemClickListener<SearchItem> clickListener;

    @Override public ResultAdapter build() {
      return new ResultAdapter(this);
    }
  }
}