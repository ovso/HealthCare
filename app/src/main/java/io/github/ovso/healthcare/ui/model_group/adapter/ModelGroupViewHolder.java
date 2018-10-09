package io.github.ovso.healthcare.ui.model_group.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.network.model.ModelGroup;
import io.github.ovso.healthcare.ui.base.adapter.BaseViewHolder;

public class ModelGroupViewHolder extends BaseViewHolder {
  @BindView(R.id.title_text_view) TextView titleTextView;

  private ModelGroupViewHolder(View itemView) {
    super(itemView);
  }

  public void bind(ModelGroup brandGroup) {
    super.bind(brandGroup);
    titleTextView.setText(brandGroup.getName());
  }

  public static ModelGroupViewHolder create(ViewGroup parent) {
    return new ModelGroupViewHolder(
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.list_item_brand, parent, false));
  }

  @OnClick(R.id.root_view) void onItemClick() {
    onRecyclerViewItemClickListener.onListItemClick(itemView, data, itemPosition);
  }
}