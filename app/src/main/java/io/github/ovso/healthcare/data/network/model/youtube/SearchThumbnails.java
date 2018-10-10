package io.github.ovso.healthcare.data.network.model.youtube;

import lombok.Getter;

@Getter public class SearchThumbnails {
  private ThumbnailsInfo defaults;
  private ThumbnailsInfo medium;
  private ThumbnailsInfo high;
}