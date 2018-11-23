package io.github.ovso.healthcare.ui.splash;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public interface SplashPresenter extends LifecycleObserver {
  @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
  public void onCreate();

  @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
  void onDestroy();

  void onBackPressed();

  interface View {
    void navigateToMain();

    void finish();

    void applyTransition();
  }
}
