package io.github.ovso.healthcare.ui.base.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import io.github.ovso.healthcare.utils.ObjectUtils;

public class MyViewRecyclerView extends RecyclerView {
  private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

  public MyViewRecyclerView(Context context) {
    super(context);
  }

  public MyViewRecyclerView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public MyViewRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override public void setAdapter(Adapter adapter) {
    super.setAdapter(adapter);
    setOnItemClickListener(adapter);
  }

  public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
    onRecyclerViewItemClickListener = listener;
    setOnItemClickListener(getAdapter());
  }

  private void setOnItemClickListener(Adapter adapter) {
    if (!ObjectUtils.isEmpty(adapter)) {
      if ((adapter instanceof BaseRecyclerAdapter)) {
        ((BaseRecyclerAdapter) adapter).setOnRecyclerViewItemClickListener(
            onRecyclerViewItemClickListener);
      }
    }
  }
}
