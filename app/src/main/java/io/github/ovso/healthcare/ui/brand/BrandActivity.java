package io.github.ovso.healthcare.ui.brand;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import butterknife.BindView;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.ui.base.BaseActivity;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.base.adapter.MyRecyclerView;
import io.github.ovso.healthcare.ui.base.adapter.OnRecyclerViewItemClickListener;
import io.github.ovso.healthcare.ui.brand.adapter.BrandAdapter;
import io.github.ovso.healthcare.ui.model_group.ModelGroupActivity;
import javax.inject.Inject;

public class BrandActivity extends BaseActivity implements BrandPresenter.View,
    OnRecyclerViewItemClickListener {

  @Inject BrandPresenter presenter;
  @Inject BrandAdapter adapter;
  @Inject BaseAdapterView adapterView;
  @BindView(R.id.recycler_view) MyRecyclerView recyclerView;

  @Override protected int getLayoutResID() {
    return R.layout.activity_brand;
  }

  @Override protected void onCreated(@Nullable Bundle savedInstanceState) {
    presenter.onCreate(getIntent());
  }

  @Override protected void onDestroy() {
    presenter.onDestroy();
    super.onDestroy();
  }

  @Override public void setupRecyclerView() {
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(adapter);
    recyclerView.setOnItemClickListener(this);
  }

  @Override public void refresh() {
    adapterView.refresh();
  }

  @Override public void showBackButton() {
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override public void setTitle(String title) {
    super.setTitle(title);
  }

  @Override public void navigateToModelGroup(int id) {
    Intent intent = new Intent(this, ModelGroupActivity.class);
    intent.putExtra("id", id);
    startActivity(intent);
  }

  @Override public void onListItemClick(View view, Object data, int itemPosition) {
    presenter.onListItemClick(data);
  }

  @Override public boolean isTitle() {
    return true;
  }
}
