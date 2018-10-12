package io.github.ovso.healthcare.ui.result;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import butterknife.BindView;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.ui.base.BaseActivity;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.base.adapter.MyRecyclerView;
import io.github.ovso.healthcare.ui.result.adapter.ResultAdapter;
import javax.inject.Inject;

public class ResultActivity extends BaseActivity implements ResultPresenter.View {

  @Inject ResultPresenter presenter;
  @Inject ResultAdapter adapter;
  @Inject BaseAdapterView adapterView;
  @BindView(R.id.recycler_view) MyRecyclerView recyclerView;

  @Override protected int getLayoutResID() {
    return R.layout.activity_result;
  }

  @Override protected void onCreated(@Nullable Bundle savedInstanceState) {
    presenter.onCreate(getIntent());
  }

  @Override public boolean isTitle() {
    return true;
  }

  @Override public void refresh() {
    adapterView.refresh();
  }

  @Override public void setupRecyclerView() {
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(adapter);
  }
}
