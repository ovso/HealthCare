package io.github.ovso.healthcare.ui.base.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.ButterKnife;
import lombok.Setter;

public class BaseViewHolder extends RecyclerView.ViewHolder {
  public BaseViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  protected Object data;
  protected void bind(Object $data) {
    data = $data;
  }

  @Setter public OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
  @Setter public int itemPosition;

}