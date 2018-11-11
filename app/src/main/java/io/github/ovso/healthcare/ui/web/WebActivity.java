package io.github.ovso.healthcare.ui.web;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.github.ovso.healthcare.R;
import io.github.ovso.healthcare.data.Portal;

public class WebActivity extends AppCompatActivity {

  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.web_view) WebView webView;
  @BindView(R.id.progress_bar) ContentLoadingProgressBar progressBar;
  private int act_id;
  private String disease;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    overridePendingTransition(R.anim.slide_up, R.anim.no_change);
    setContentView(R.layout.activity_web);
    ButterKnife.bind(this);

    setupActionBar();
    setupData();
    setupWebView();
    load();
  }

  private void setupActionBar() {
    setSupportActionBar(toolbar);
    //getSupportActionBar().setDisplayShowTitleEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
  }

  private void load() {
    webView.loadUrl(Portal.toUrl(act_id, disease));
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

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    finish();
    return true;
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
      super.onPageFinished(view, url);
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

  }

  @OnClick(R.id.web_browser_button)
  void onWebBrowClick() {

  }
}
