package io.github.ovso.healthcare.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor public enum KeyName {
  POSITION("position"),
  PAGE_TOKEN("pageToken"),
  Q("q"),
  MAX_RESULTS("maxResults"),
  ORDER("order"),
  TYPE("type"),
  VIDEO_SYNDICATED("videoSyndicated"),
  VIDEO_TYPE("videoType"),
  KEY("key"),
  PART("part"),
  DISEASE_NAME("disease_name");
  private String value;

}