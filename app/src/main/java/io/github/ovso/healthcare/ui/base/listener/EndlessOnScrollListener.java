package io.github.ovso.healthcare.ui.base.listener;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.ovso.healthcare.ui.base.IBuilder;
import io.github.ovso.healthcare.utils.ObjectUtils;
import lombok.Setter;
import lombok.experimental.Accessors;

public class EndlessOnScrollListener extends RecyclerView.OnScrollListener {

  private int visibleThreshold = 1;
  private boolean loading;
  private GridLayoutManager gridLayoutManager;
  private LinearLayoutManager linearLayoutManager;
  private OnLoadMoreListener onLoadMoreListener;

  EndlessOnScrollListener(EndlessOnScrollListener.Builder builder) {
    if (builder.layoutManager instanceof LinearLayoutManager) {
      linearLayoutManager = (LinearLayoutManager) builder.layoutManager;
    } else {
      gridLayoutManager = (GridLayoutManager) builder.layoutManager;
    }
    onLoadMoreListener = builder.onLoadMoreListener;
    visibleThreshold = builder.visibleThreshold;
  }

  @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);

    final int totalItemCount = getTotalItemCount();
    final int lastVisibleItem = getLastVisibleItem();
    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
      loading = true;
      if (onLoadMoreListener != null) {
        onLoadMoreListener.onLoadMore();
      }
    }
  }

  private int getTotalItemCount() {
    return !ObjectUtils.isEmpty(linearLayoutManager) ?
        linearLayoutManager.getItemCount()
        : gridLayoutManager.getItemCount();
  }

  private int getLastVisibleItem() {
    return !ObjectUtils.isEmpty(linearLayoutManager) ?
        linearLayoutManager.findLastVisibleItemPosition()
        : gridLayoutManager.findLastVisibleItemPosition();
  }

  public void setLoaded() {
    loading = false;
  }

  public interface OnLoadMoreListener {
    void onLoadMore();
  }

  public static class Builder implements IBuilder<EndlessOnScrollListener> {
    @Setter @Accessors(chain = true) private int visibleThreshold = 1;
    @Setter @Accessors(chain = true) private RecyclerView.LayoutManager layoutManager;
    @Setter @Accessors(chain = true) private OnLoadMoreListener onLoadMoreListener;

    @Override public EndlessOnScrollListener build() {
      return new EndlessOnScrollListener(this);
    }
  }
}
