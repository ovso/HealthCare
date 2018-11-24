package io.github.ovso.healthcare.utils;

import android.content.Context;
import android.content.Intent;
import io.github.ovso.healthcare.R;
import timber.log.Timber;

public class ActivityUtils {
  private ActivityUtils() {
  }

  public static void share(Context context, String subject, String text) {
    try {
      Intent i = new Intent(Intent.ACTION_SEND);
      i.setType("text/plain");
      i.putExtra(Intent.EXTRA_SUBJECT, subject);
      i.putExtra(Intent.EXTRA_TEXT, text);
      context.startActivity(Intent.createChooser(i, context.getString(R.string.all_appshare)));
    } catch (Exception e) {
      Timber.d(e);
    }
  }
}
