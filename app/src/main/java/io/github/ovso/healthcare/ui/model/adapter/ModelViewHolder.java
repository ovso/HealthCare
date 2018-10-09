package io.github.ovso.healthcare.ui.model.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.network.model.Model;
import io.github.ovso.healthcare.ui.base.adapter.BaseViewHolder;

public class ModelViewHolder extends BaseViewHolder {
  @BindView(R.id.title_text_view) TextView titleTextView;

  private ModelViewHolder(View itemView) {
    super(itemView);
  }

  public void bind(Model model) {
    super.bind(model);
    titleTextView.setText(model.getName());
  }

  public static ModelViewHolder create(ViewGroup parent) {
    return new ModelViewHolder(
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.list_item_brand, parent, false));
  }

  @OnClick(R.id.root_view) void onItemClick() {
    onRecyclerViewItemClickListener.onListItemClick(itemView, data, itemPosition);
  }
}