package io.github.ovso.healthcare.data.network.model.youtube;

import lombok.Getter;

@Getter public class SearchItem {
  private String kind;
  private String etag;
  private SearchItemId id;
  private Snippet snippet;
}