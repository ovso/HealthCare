package io.github.ovso.healthcare.ui.model;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import butterknife.BindView;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.network.model.Car;
import io.github.ovso.healthcare.ui.base.BaseActivity;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.base.adapter.MyViewRecyclerView;
import io.github.ovso.healthcare.ui.base.adapter.OnRecyclerViewItemClickListener;
import io.github.ovso.healthcare.ui.detail.DetailActivity;
import io.github.ovso.healthcare.ui.main.MainActivity;
import io.github.ovso.healthcare.ui.model.adapter.ModelAdapter;
import javax.inject.Inject;

public class ModelActivity extends BaseActivity implements ModelPresenter.View,
    OnRecyclerViewItemClickListener {

  @Inject ModelPresenter presenter;
  @Inject ModelAdapter adapter;
  @Inject BaseAdapterView adapterView;
  @BindView(R.id.recycler_view) MyViewRecyclerView recyclerView;

  @Override protected int getLayoutResID() {
    return R.layout.activity_model;
  }

  @Override protected void onCreated(@Nullable Bundle savedInstanceState) {
    presenter.onCreate(getIntent());
  }

  @Override public void onListItemClick(View view, Object data, int itemPosition) {
    presenter.onListItemClick(data);
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

  @Override public void navigateToModel(int id) {

  }

  @Override public void navigateToDetail(Car car) {
    Intent intent = new Intent(this, DetailActivity.class);
    intent.putExtra("data", car);
    startActivity(intent);
  }

  @Override public void navigateToMain(int id) {
    Intent intent = new Intent(this, MainActivity.class);
    intent.putExtra("id", id);
    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
  }

  @Override public void navigateToMain(int id, String name) {
    Intent intent = new Intent(this, MainActivity.class);
    intent.putExtra("id", id);
    intent.putExtra("name", name);
    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
  }

  @Override public boolean isTitle() {
    return true;
  }
}