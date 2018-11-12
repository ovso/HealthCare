package io.github.ovso.healthcare.ui.base.adapter;

import androidx.recyclerview.widget.RecyclerView;
import lombok.Setter;

public abstract class BaseRecyclerAdapter
    extends RecyclerView.Adapter<BaseViewHolder> {

  @Setter protected OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
}