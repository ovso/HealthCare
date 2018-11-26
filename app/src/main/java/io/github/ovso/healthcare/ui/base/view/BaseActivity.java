package io.github.ovso.healthcare.ui.base.view;

import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.github.ovso.healthcare.R;

public abstract class BaseActivity extends AdsActivity {
  @BindView(R.id.toolbar) protected Toolbar toolbar;
  @BindView(R.id.framelayout_all_adcontainer) ViewGroup adContainer;
  private Unbinder bind;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResID());
    bind = ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(isTitle());
    adContainer.addView(adView);
  }

  protected abstract int getLayoutResID();

  @Override protected void onDestroy() {
    super.onDestroy();
    if (bind != null) {
      bind.unbind();
    }
  }

  public boolean isTitle() {
    return false;
  }
}