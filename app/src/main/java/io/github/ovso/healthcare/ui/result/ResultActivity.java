package io.github.ovso.healthcare.ui.result;

import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import com.airbnb.lottie.LottieAnimationView;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.network.model.youtube.SearchItem;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.base.listener.BaseOnItemClickListener;
import io.github.ovso.healthcare.ui.base.listener.EndlessOnScrollListener;
import io.github.ovso.healthcare.ui.base.view.BaseActivity;
import io.github.ovso.healthcare.ui.result.adapter.ResultAdapter;
import io.github.ovso.healthcare.ui.video.VideoActivity;
import io.github.ovso.healthcare.ui.web.WebActivity;
import javax.inject.Inject;

public class ResultActivity extends BaseActivity implements ResultPresenter.View,
    BaseOnItemClickListener<SearchItem>, EndlessOnScrollListener.OnLoadMoreListener {

  @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefresh;
  @BindView(R.id.recycler_view) RecyclerView recyclerView;
  @BindView(R.id.empty_animation) LottieAnimationView emptyAniView;

  @Inject ResultPresenter presenter;
  @Inject ResultAdapter adapter;
  @Inject BaseAdapterView adapterView;

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
    EndlessOnScrollListener scrollListener =
        new EndlessOnScrollListener.Builder().setLayoutManager(recyclerView.getLayoutManager())
            .setOnLoadMoreListener(this)
            .setVisibleThreshold(5)
            .build();
    recyclerView.addOnScrollListener(scrollListener);
    recyclerView.setTag(scrollListener);
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

  @Override public void setupSwipeRefresh() {
    swipeRefresh.setColorSchemeResources(R.color.colorPrimaryDark);
    swipeRefresh.setOnRefreshListener(() -> presenter.onSwipeRefresh());
  }

  @Override public void showLoading() {
    swipeRefresh.setRefreshing(true);
  }

  @Override public void hideLoading() {
    swipeRefresh.setRefreshing(false);
  }

  @Override public void setLoaded() {
    ((EndlessOnScrollListener) recyclerView.getTag()).setLoaded();
  }

  @Override public void showEmptyAni() {
    emptyAniView.playAnimation();
    emptyAniView.setVisibility(View.VISIBLE);
  }

  @Override public void hideEmpthAni() {
    emptyAniView.cancelAnimation();
    emptyAniView.setVisibility(View.GONE);
  }

  @Override public void hideRecyclerView() {
    recyclerView.setVisibility(View.GONE);
  }

  @Override public void showRecyclerView() {
    recyclerView.setVisibility(View.VISIBLE);
  }

  @Override public void navigateToBrowser(String url) {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse(url));
    startActivity(intent);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_result, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    presenter.onOptionsItemSelected(item.getItemId());
    return super.onOptionsItemSelected(item);
  }

  @Override public void onItemClick(SearchItem item) {
    presenter.onItemClick(item);
  }

  @Override public void onLoadMore() {
    presenter.onLoadMore();
  }
}