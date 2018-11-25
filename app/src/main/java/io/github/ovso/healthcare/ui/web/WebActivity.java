package io.github.ovso.healthcare.ui.web;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ContentLoadingProgressBar;
import butterknife.BindView;
import butterknife.OnClick;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.Portal;
import io.github.ovso.healthcare.ui.base.view.BaseActivity;

public class WebActivity extends BaseActivity {

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.web_view) WebView webView;
  @BindView(R.id.progress_bar) ContentLoadingProgressBar progressBar;
  @BindView(R.id.web_back_button) ImageButton webBackButton;
  @BindView(R.id.web_forward_button) ImageButton webForwardButton;
  private int act_id;
  private String disease;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    overridePendingTransition(R.anim.slide_up, R.anim.no_change);

    setupActionBar();
    setupData();
    setupWebView();
    load();
  }

  @Override protected int getLayoutResID() {
    return R.layout.activity_web;
  }

  private void setupActionBar() {
    setSupportActionBar(toolbar);
    //getSupportActionBar().setDisplayShowTitleEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
  }

  private void load() {
    webView.loadUrl(Portal.toUrl(act_id, disease));
    updateWebNaviButton();
  }

  private void setupData() {
    Intent intent = getIntent();
    act_id = intent.getIntExtra("act_id", 0);
    disease = intent.getStringExtra("disease");
    setTitle(R.string.search);
  }

  private void setupWebView() {
    webView.getSettings().setJavaScriptEnabled(true);
    webView.getSettings().setDomStorageEnabled(true);
    webView.getSettings().setAppCacheEnabled(true);
    webView.getSettings().setSupportMultipleWindows(true);
    webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    webView.getSettings().setGeolocationEnabled(true);
    webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
    webView.setWebChromeClient(new MyWebChromeClient());
    webView.setWebViewClient(new MyWebViewClient());
  }

  private class MyWebChromeClient extends WebChromeClient {
    @Override public void onProgressChanged(WebView view, int newProgress) {
      progressBar.setProgress(newProgress);
      super.onProgressChanged(view, newProgress);
    }
  }

  @Override public void finish() {
    super.finish();
    overridePendingTransition(R.anim.no_change, R.anim.slide_down);
  }

  private class MyWebViewClient extends WebViewClient {

    @Override public void onPageStarted(WebView view, String url, Bitmap favicon) {
      progressBar.show();
      super.onPageStarted(view, url, favicon);
    }

    @Override public void onPageFinished(WebView view, String url) {
      progressBar.hide();
      updateWebNaviButton();
      super.onPageFinished(view, url);
    }
  }

  private void updateWebNaviButton() {
    if (webView.canGoBack()) {
      int color = ContextCompat.getColor(this, android.R.color.black);
      webBackButton.setImageTintList(ColorStateList.valueOf(color));
      webBackButton.setClickable(true);
    } else {
      int color = ContextCompat.getColor(this, android.R.color.darker_gray);
      webBackButton.setImageTintList(ColorStateList.valueOf(color));
      webBackButton.setClickable(false);
    }

    if (webView.canGoForward()) {
      int color = ContextCompat.getColor(this, android.R.color.black);
      webForwardButton.setImageTintList(ColorStateList.valueOf(color));
      webForwardButton.setClickable(true);
    } else {
      int color = ContextCompat.getColor(this, android.R.color.darker_gray);
      webForwardButton.setImageTintList(ColorStateList.valueOf(color));
      webForwardButton.setClickable(false);
    }
  }

  @OnClick(R.id.web_back_button)
  void onWebBackClick() {
    if (webView.canGoBack()) {
      webView.goBack();
    }
  }

  @OnClick(R.id.web_forward_button)
  void onWebForwClick() {
    if (webView.canGoForward()) {
      webView.goForward();
    }
  }

  @OnClick(R.id.web_refresh_button)
  void onWebRefrClick() {
    webView.reload();
  }

  @OnClick(R.id.web_share_button)
  void onWebSharClick() {
    Intent sendIntent = new Intent();
    sendIntent.setAction(Intent.ACTION_SEND);
    sendIntent.putExtra(Intent.EXTRA_TEXT, webView.getUrl());
    sendIntent.setType("text/plain");
    startActivity(Intent.createChooser(sendIntent, getResources().getString(R.string.web_share)));
  }

  @OnClick(R.id.web_browser_button)
  void onWebBrowClick() {
    Intent intent = new Intent(Intent.ACTION_VIEW);
    intent.setData(Uri.parse(webView.getUrl()));
    startActivity(intent);
  }
}
