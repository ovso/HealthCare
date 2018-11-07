package io.github.ovso.healthcare.data;

import io.github.ovso.healthcare.R;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter public enum Portal {
  GOOGLE(R.id.action_google, "https://google.co.kr/search?q="),
  NAVER(R.id.action_naver, "https://m.search.naver.com/search.naver?query="),
  DAUM(R.id.action_daum, "https://m.search.daum.net/search?q=");

  private int act_id;
  private String url;

  public static String toUrl(int act_id, String q) {
    for (Portal portal : Portal.values()) {
      if (act_id == portal.act_id) {
        return portal.url + q;
      }
    }

    return Portal.GOOGLE.url + q;
  }
}