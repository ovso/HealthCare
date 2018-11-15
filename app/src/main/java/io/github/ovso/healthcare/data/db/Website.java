package io.github.ovso.healthcare.data.db;

import lombok.Getter;
import lombok.ToString;

@ToString @Getter public class Website {
  private String title;
  private String link;
  private String description;
  private int position;
}
