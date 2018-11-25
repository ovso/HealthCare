package io.github.ovso.healthcare.utils;

import android.content.Context;
import android.content.ContextWrapper;
import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.google.android.gms.ads.MobileAds;
import com.pixplicity.easyprefs.library.Prefs;
import io.fabric.sdk.android.Fabric;
import io.github.ovso.healthcare.App;
import io.github.ovso.healthcare.BuildConfig;
import io.github.ovso.healthcare.Security;
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

  public static void prefs(Context context) {
    new Prefs.Builder()
        .setContext(context)
        .setMode(ContextWrapper.MODE_PRIVATE)
        .setPrefsName(context.getPackageName())
        .setUseDefaultSharedPreference(true)
        .build();
  }

  public static void stetho(Context content) {
    Stetho.initializeWithDefaults(content);
  }

  public static void ads(Context context) {
    MobileAds.initialize(context, Security.ADMOB_APP_ID.getValue());
  }
}