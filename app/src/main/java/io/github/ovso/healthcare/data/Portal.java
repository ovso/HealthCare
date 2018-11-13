package io.github.ovso.healthcare.data;

import io.github.ovso.healthcare.R;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter public enum Portal {
  GOOGLE(R.id.action_google, "https://google.co.kr/search?q="),
  NAVER(R.id.action_naver, "https://m.search.naver.com/search.naver?where=m_video&query="),
  DAUM(R.id.action_daum, "https://m.search.daum.net/search?w=vclip&q=");

  private int act_id;
  private String url;

  public static String toUrl(int act_id, String q) {
    switch (act_id) {
      case R.id.action_google:
        return Portal.GOOGLE.getUrl() + q + "&tbm=vid";
      case R.id.action_naver:
        return Portal.NAVER.getUrl() + q;
      case R.id.action_daum:
        return Portal.DAUM.getUrl() + q;
      default:
        return Portal.GOOGLE.getUrl() + q + "&tbm=vid";
    }
  }
}