package io.github.ovso.healthcare.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import br.com.liveo.searchliveo.SearchLiveo;
import butterknife.BindView;
import com.airbnb.lottie.LottieAnimationView;
import com.fondesa.recyclerviewdivider.RecyclerViewDivider;
import com.google.android.material.navigation.NavigationView;
import de.psdev.licensesdialog.LicensesDialogFragment;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.KeyName;
import io.github.ovso.healthcare.data.db.model.DiseaseEntity;
import io.github.ovso.healthcare.ui.base.BaseActivity;
import io.github.ovso.healthcare.ui.base.adapter.BaseAdapterView;
import io.github.ovso.healthcare.ui.like.LikeActivity;
import io.github.ovso.healthcare.ui.main.adapter.MainAdapter;
import io.github.ovso.healthcare.ui.base.listener.DiseaseOnItemClickListener;
import io.github.ovso.healthcare.ui.result.ResultActivity;
import javax.inject.Inject;
import spencerstudios.com.bungeelib.Bungee;

public class MainActivity extends BaseActivity implements MainPresenter.View,
    DiseaseOnItemClickListener<DiseaseEntity>, SearchLiveo.OnSearchListener,
    NavigationView.OnNavigationItemSelectedListener {

  @Inject MainPresenter presenter;
  @Inject MainAdapter adapter;
  @Inject BaseAdapterView adapterView;
  @BindView(R.id.recycler_view) RecyclerView recyclerView;
  @BindView(R.id.drawer_layout) DrawerLayout drawer;
  @BindView(R.id.navigation_view) NavigationView navigationView;
  @BindView(R.id.search_liveo) SearchLiveo searchLiveo;
  @BindView(R.id.empty_animation) LottieAnimationView emptyAniView;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bungee.fade(this);
  }

  @Override protected int getLayoutResID() {
    return R.layout.activity_main;
  }

  @Override public void setupRecyclerView() {
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(adapter);
    RecyclerViewDivider.with(this)
        .size(1)
        .color(ContextCompat.getColor(this, android.R.color.darker_gray))
        .build()
        .addTo(recyclerView);
  }

  @Override public void refresh() {
    adapterView.refresh();
  }

  @Override public void navigateToResult(DiseaseEntity disease) {
    Intent intent = new Intent(this, ResultActivity.class);
    intent.putExtra(KeyName.DISEASE_NAME.getValue(), disease.name);
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
  }

  @Override public boolean isTitle() {
    return true;
  }

  @Override public void setupSearchLiveo() {
    searchLiveo.with(this).build();
    searchLiveo.showVoice();
    searchLiveo.minToSearch(0);
  }

  @Override public void closeDrawer() {
    drawer.closeDrawer(GravityCompat.START);
  }

  @Override public void setTitle(String title) {
    super.setTitle(title);
  }

  @Override public void showNotiDialog() {
    new AlertDialog.Builder(this).setMessage(R.string.comming_soon_favorite)
        .setPositiveButton(android.R.string.ok, (dialog, which) -> dialog.dismiss())
        .show();
  }

  @Override public void setupNavigationView() {
    navigationView.setNavigationItemSelectedListener(this);
  }

  @Override public void showEmptyAni() {
    emptyAniView.playAnimation();
    emptyAniView.setVisibility(View.VISIBLE);
  }

  @Override public void hideEmpthAni() {
    emptyAniView.cancelAnimation();
    emptyAniView.setVisibility(View.GONE);
  }

  @Override
  public void navigateToLike() {
    Intent intent = new Intent(this, LikeActivity.class);
    startActivity(intent);
  }

  @Override public void showMessage(String msg) {
    new AlertDialog.Builder(this).setMessage(msg)
        .setPositiveButton(android.R.string.ok, null)
        .show();
  }

  @Override public void showLicenses() {
    final LicensesDialogFragment fragment = new LicensesDialogFragment.Builder(this)
        .setNotices(R.raw.licenses)
        .setShowFullLicenseText(false)
        .setIncludeOwnLicense(true)
        .build();

    fragment.show(getSupportFragmentManager(), null);
  }

  @Override public void showAppVersion(String versions) {
    TextView view = navigationView.getHeaderView(0).findViewById(R.id.version_text_view);
    view.setText(versions);
  }

  @Override public void onItemClick(DiseaseEntity disease) {
    presenter.onItemClick(disease);
  }

  @Override public void onItemLikeClick(DiseaseEntity item, boolean checked) {
    presenter.onItemLikeClick(item, checked);
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

  @Override public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
    return presenter.onNavigationItemSelected(menuItem.getItemId());
  }
}