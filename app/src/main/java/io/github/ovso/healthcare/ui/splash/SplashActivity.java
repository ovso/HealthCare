package io.github.ovso.healthcare.ui.splash;

import android.content.Intent;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.ui.base.BaseActivity;
import io.github.ovso.healthcare.ui.main.MainActivity;
import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashPresenter.View {

  @Inject SplashPresenter presenter;

  @Override
  public void navigateToMain() {
    startActivity(new Intent(getApplicationContext(), MainActivity.class));
  }

  @Override public void onBackPressed() {
    presenter.onBackPressed();
    super.onBackPressed();
  }

  @Override protected int getLayoutResID() {
    return R.layout.activity_splash;
  }
}