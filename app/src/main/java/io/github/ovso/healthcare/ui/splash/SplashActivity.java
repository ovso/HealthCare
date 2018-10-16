package io.github.ovso.healthcare.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.ui.main.MainActivity;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity {

  private Disposable subscribe;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    subscribe =
        Single.just("splash").delay(500, TimeUnit.MILLISECONDS).subscribe(o -> navigateToMain());
  }

  private void navigateToMain() {
    startActivity(new Intent(getApplicationContext(), MainActivity.class));
    finish();
  }

  @Override public void onBackPressed() {
    super.onBackPressed();
    if (subscribe != null) {
      subscribe.dispose();
    }
  }

  @Override protected void onDestroy() {
    if (subscribe != null) {
      subscribe.dispose();
    }
    super.onDestroy();
  }
}