package io.github.ovso.healthcare.ui.base.adapter;

import android.support.v7.widget.RecyclerView;
import lombok.Setter;

public abstract class BaseRecyclerAdapter
    extends RecyclerView.Adapter<BaseViewHolder> {

  @Setter protected OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
}