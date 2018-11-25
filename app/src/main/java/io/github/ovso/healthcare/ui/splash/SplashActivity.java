package io.github.ovso.healthcare.ui.splash;

import android.content.Intent;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.ui.base.view.BaseActivity;
import io.github.ovso.healthcare.ui.main.MainActivity;
import javax.inject.Inject;
import spencerstudios.com.bungeelib.Bungee;

public class SplashActivity extends BaseActivity implements SplashPresenter.View {

  @Inject SplashPresenter presenter;

  @Override
  public void navigateToMain() {
    startActivity(new Intent(getApplicationContext(), MainActivity.class));
  }

  @Override public void pendingTransition() {
    Bungee.slideLeft(this);
  }

  @Override protected int getLayoutResID() {
    return R.layout.activity_splash;
  }
}