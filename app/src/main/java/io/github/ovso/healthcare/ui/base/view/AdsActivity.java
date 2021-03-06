package io.github.ovso.healthcare.ui.base.view;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class AdsActivity extends DaggerAppCompatActivity {
  protected InterstitialAd interstitialAd;
  protected AdView adView;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    interstitialAd = Ads.getAdmobInterstitialAd(getApplicationContext());
    interstitialAd.setAdListener(new AdListener() {
      @Override public void onAdClosed() {
        super.onAdClosed();
        finish();
      }
    });
    adView = Ads.getAdmobView(getApplicationContext());
  }

  @Override public void onBackPressed() {
    finishHandling();
  }

  private void finishHandling() {
    if (interstitialAd.isLoaded()) {
      interstitialAd.show();
    } else {
      finish();
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finishHandling();
    }
    return true;
  }
}
