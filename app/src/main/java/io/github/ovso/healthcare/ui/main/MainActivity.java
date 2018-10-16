package io.github.ovso.healthcare.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import br.com.liveo.searchliveo.SearchLiveo;
import butterknife.BindView;
import com.fondesa.recyclerviewdivider.RecyclerViewDivider;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.KeyName;
import io.github.ovso.healthcare.data.network.model.Disease;
import io.github.ovso.healthcare.ui.base.BaseActivity;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.base.adapter.MyRecyclerView;
import io.github.ovso.healthcare.ui.base.adapter.OnRecyclerViewItemClickListener;
import io.github.ovso.healthcare.ui.main.adapter.MainAdapter;
import io.github.ovso.healthcare.ui.result.ResultActivity;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainPresenter.View,
    OnRecyclerViewItemClickListener<Disease>, SearchLiveo.OnSearchListener {

  @Inject MainPresenter presenter;
  @Inject MainAdapter adapter;
  @Inject BaseAdapterView adapterView;
  @BindView(R.id.recycler_view) MyRecyclerView recyclerView;
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
    RecyclerViewDivider.with(this)
        .size(1)
        .color(ContextCompat.getColor(this, android.R.color.darker_gray))
        .build()
        .addTo(recyclerView);
  }

  @Override public void refresh() {
    adapterView.refresh();
  }

  @Override public void navigateToDetail(Disease disease) {
    Intent intent = new Intent(this, ResultActivity.class);
    intent.putExtra(KeyName.DISEASE_NAME.getValue(), disease.getName());
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
    searchLiveo.minToSearch(0);
  }

  @Override public void closeDrawer() {
    drawer.closeDrawer(GravityCompat.START);
  }

  @Override public void onItemClick(Disease disease) {
    presenter.onItemClick(disease);
  }

  @Override public void onItemLikeClick(Disease item) {
    presenter.onItemLikeClick(item);
  }

  @Override public void changedSearch(CharSequence charSequence) {
    presenter.changedSearch(charSequence);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (data != null) {
      if (requestCode == SearchLiveo.REQUEST_CODE_SPEECH_INPUT) {
        searchLiveo.resultVoice(requestCode, resultCode, data);
      }
    }
  }

  @Override public void onBackPressed() {

    presenter.onBackPressed(drawer.isDrawerOpen(GravityCompat.START));
  }
}