package io.github.ovso.healthcare.ui.brand.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.network.model.Brand;
import io.github.ovso.healthcare.ui.base.adapter.BaseViewHolder;

public class BrandViewHolder extends BaseViewHolder {
  @BindView(R.id.title_text_view) TextView titleTextView;

  private BrandViewHolder(View itemView) {
    super(itemView);
  }

  public void bind(Brand brand) {
    super.bind(brand);
    titleTextView.setText(brand.getName());
  }

  public static BrandViewHolder create(ViewGroup parent) {
    return new BrandViewHolder(
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.list_item_brand, parent, false));
  }

  @OnClick(R.id.root_view) void onItemClick() {
    onRecyclerViewItemClickListener.onListItemClick(itemView, data, itemPosition);
  }
}