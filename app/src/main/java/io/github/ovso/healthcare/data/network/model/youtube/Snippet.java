package io.github.ovso.healthcare.data.network.model.youtube;

import java.util.Date;
import lombok.Getter;

@Getter public class Snippet {
  private Date publishedAt;
  private String channelId;
  private String title;
  private String description;
  private SearchThumbnails thumbnails;
  private String channelTitle;
  private String liveBroadcastContent;
}