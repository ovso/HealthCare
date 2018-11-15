package io.github.ovso.healthcare.data.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "WEBSITE_INFO") public class WebsiteEntity {
  @PrimaryKey(autoGenerate = true)
  public int id;

  @ColumnInfo(name = "TITLE")
  public String title;
  @ColumnInfo(name = "LINK")
  public String link;
  @ColumnInfo(name = "DESC")
  public String description;

  @ColumnInfo(name =  "POSITION")
  public int position;

  public static WebsiteEntity convertWebsiteToEntiry(Website item) {
    WebsiteEntity entity = new WebsiteEntity();
    entity.description = item.getDescription();
    entity.link = item.getLink();
    entity.title = item.getTitle();
    entity.position = item.getPosition();
    return entity;
  }
}