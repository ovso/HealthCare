package io.github.ovso.healthcare.data.network;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor public enum EndPoint {

  RESULT("https://www.googleapis.com");
  private String url;
}