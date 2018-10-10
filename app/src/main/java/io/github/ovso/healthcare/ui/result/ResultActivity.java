package io.github.ovso.healthcare.ui.result;

import android.os.Bundle;
import android.support.annotation.Nullable;
import butterknife.BindView;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.ui.base.BaseActivity;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.base.adapter.MyRecyclerView;
import io.github.ovso.healthcare.ui.result.adapter.ResultAdapter;
import javax.inject.Inject;

public class ResultActivity extends BaseActivity implements ResultPresenter.View {

  //@Inject ResultPresenter presenter;
  @Inject ResultAdapter adapter;
  @Inject BaseAdapterView adapterView;
  @BindView(R.id.recycler_view) MyRecyclerView recyclerView;


  @Override protected int getLayoutResID() {
    return R.layout.activity_result;
  }

  @Override protected void onCreated(@Nullable Bundle savedInstanceState) {
    //presenter.onCreate();
  }
}
