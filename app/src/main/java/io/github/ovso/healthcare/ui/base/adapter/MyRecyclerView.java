package io.github.ovso.healthcare.ui.base.adapter;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import io.github.ovso.healthcare.utils.ObjectUtils;

public class MyRecyclerView extends RecyclerView {
  private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

  public MyRecyclerView(Context context) {
    super(context);
  }

  public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
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
