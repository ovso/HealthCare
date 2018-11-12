package io.github.ovso.healthcare.ui.base.adapter;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;

public class BaseViewHolder<T> extends RecyclerView.ViewHolder {
  public BaseViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  protected T data;

  protected void bind(T $data) {
    data = $data;
  }
}