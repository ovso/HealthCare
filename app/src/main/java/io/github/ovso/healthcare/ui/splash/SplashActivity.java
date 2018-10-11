package io.github.ovso.healthcare.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);

    navigateToMain();
  }

  private void navigateToMain() {
    startActivity(new Intent(getApplicationContext(), MainActivity.class));
    finish();
  }
}
