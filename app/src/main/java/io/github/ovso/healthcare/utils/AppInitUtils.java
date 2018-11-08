package io.github.ovso.healthcare.utils;

import android.content.Context;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import io.github.ovso.healthcare.App;
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

  public static void crash(Context context) {
    if (!BuildConfig.DEBUG) {
      Fabric.with(context, new Crashlytics());
    }
  }
}