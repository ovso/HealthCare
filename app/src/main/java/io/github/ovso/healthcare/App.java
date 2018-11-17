package io.github.ovso.healthcare;

import android.content.Context;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.github.ovso.healthcare.di.DaggerAppComponent;
import io.github.ovso.healthcare.utils.AppInitUtils;
import lombok.Getter;

public class App extends DaggerApplication {

  @Getter public static Context instance;

  @Override public void onCreate() {
    super.onCreate();
    instance = this;
    AppInitUtils.timer();
    AppInitUtils.crash(this);
    AppInitUtils.prefs(this);
  }

  @Override protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    return DaggerAppComponent.builder().application(this).build();
  }
}
