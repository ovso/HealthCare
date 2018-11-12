package io.github.ovso.healthcare.ui.result;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.network.model.youtube.SearchItem;
import io.github.ovso.healthcare.ui.base.BaseActivity;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.base.adapter.MyRecyclerView;
import io.github.ovso.healthcare.ui.base.listener.BaseOnItemClickListener;
import io.github.ovso.healthcare.ui.result.adapter.ResultAdapter;
import io.github.ovso.healthcare.ui.video.VideoActivity;
import io.github.ovso.healthcare.ui.web.WebActivity;
import javax.inject.Inject;

public class ResultActivity extends BaseActivity implements ResultPresenter.View,
    BaseOnItemClickListener<SearchItem> {

  @Inject ResultPresenter presenter;
  @Inject ResultAdapter adapter;
  @Inject BaseAdapterView adapterView;
  @BindView(R.id.recycler_view) MyRecyclerView recyclerView;

  @Override protected int getLayoutResID() {
    return R.layout.activity_result;
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

  @Override public void setupActionBar() {
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @Override public void setTitle(String title) {
    super.setTitle(title);
  }

  @Override public void navigateToWeb(int itemId, String diseaseName) {
    Intent intent = new Intent(getApplicationContext(), WebActivity.class);
    intent.putExtra("act_id", itemId);
    intent.putExtra("disease", diseaseName);
    startActivity(intent);
  }

  @Override public void navigateToVideo(SearchItem item) {
    Intent intent = new Intent(getApplicationContext(), VideoActivity.class);
    intent.putExtra("video_id", item.getId().getVideoId());
    startActivity(intent);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_result, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    return presenter.onOptionsItemSelected(item.getItemId());
  }

  @Override public void onItemClick(SearchItem item) {
    presenter.onItemClick(item);
  }
}