package io.github.ovso.healthcare.ui.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife;
import lombok.Setter;

public class BaseViewHolder<T> extends RecyclerView.ViewHolder {
  public BaseViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  protected T data;

  protected void bind(T $data) {
    data = $data;
  }

  @Setter public OnRecyclerViewItemClickListener<T> onRecyclerViewItemClickListener;
  @Setter public int itemPosition;
}