package io.github.ovso.healthcare.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor public enum RequestType {
  ALL(1), SINGLE(2);
  private int value;
}
