package io.github.ovso.healthcare.utils;

import io.github.ovso.healthcare.BuildConfig;
import timber.log.Timber;

public class AppInitUtils {

  private AppInitUtils() {

  }

  public static void timer() {
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }
}