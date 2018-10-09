package io.github.ovso.healthcare.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import br.com.liveo.searchliveo.SearchLiveo;
import butterknife.BindView;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.ui.base.BaseActivity;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.base.adapter.MyViewRecyclerView;
import io.github.ovso.healthcare.ui.base.adapter.OnRecyclerViewItemClickListener;
import io.github.ovso.healthcare.ui.detail.DetailActivity;
import io.github.ovso.healthcare.ui.main.adapter.MainAdapter;
import javax.inject.Inject;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements MainPresenter.View,
    OnRecyclerViewItemClickListener, SearchLiveo.OnSearchListener {

  @Inject MainPresenter presenter;
  @Inject MainAdapter adapter;
  @Inject BaseAdapterView adapterView;
  @BindView(R.id.recycler_view) MyViewRecyclerView recyclerView;
  @BindView(R.id.drawer_layout) DrawerLayout drawer;
  @BindView(R.id.navigation_view) NavigationView navigationView;
  @BindView(R.id.search_liveo) SearchLiveo searchLiveo;

  @Override protected int getLayoutResID() {
    return R.layout.activity_main;
  }

  @Override protected void onCreated(@Nullable Bundle savedInstanceState) {
    presenter.onCreated();
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public void setupRecyclerView() {
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(adapter);
    recyclerView.setOnItemClickListener(this);
  }

  @Override public void refresh() {
    adapterView.refresh();
  }

  @Override public void navigateToDetail(Object data) {
    Intent intent = new Intent(this, DetailActivity.class);
    intent.putExtra("data", (Parcelable) data);
    startActivity(intent);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.action_search) {
      searchLiveo.show();
      return true;
    } else {
      return super.onOptionsItemSelected(item);
    }
  }

  @Override public void setupToolbar() {
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
    navigationView.setNavigationItemSelectedListener(
        item -> {
          drawer.closeDrawer(GravityCompat.START);
          return true;
        });
  }

  @Override public void setupSearchLiveo() {
    searchLiveo.with(this).build();
    searchLiveo.showVoice();
    searchLiveo.minToSearch(1);
  }

  @Override public void onListItemClick(View view, Object data, int itemPosition) {
    //presenter.onListItemClick(data, itemPosition);
  }

  @Override public void changedSearch(CharSequence charSequence) {
    Timber.d("charSequence = " + charSequence);
  }
}