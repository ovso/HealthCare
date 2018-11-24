package io.github.ovso.healthcare.data.network;

import io.github.ovso.healthcare.BuildConfig;
import io.reactivex.Observable;
import javax.inject.Inject;
import org.jsoup.Jsoup;

public class VersionRequest {
  private final static String store_url =
      "https://play.google.com/store/apps/details?id=";
  private final static String TEST_URL =
      "https://play.google.com/store/apps/details?id=kr.blogspot.ovsoce.hotkey";

  @Inject
  public VersionRequest() {
  }

  public Observable<String> version() {
    return Observable.fromCallable(
        () -> Jsoup.connect(getUrl()).timeout(5000).get())
        .map($doc -> $doc.select(".hAyfc .htlgb").get(7).ownText());
  }

  private String getUrl() {
    return store_url + BuildConfig.APPLICATION_ID;
  }
}