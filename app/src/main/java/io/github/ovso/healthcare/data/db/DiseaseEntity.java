package io.github.ovso.healthcare.data.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "DISEASE_INFO") public class DiseaseEntity {
  @PrimaryKey(autoGenerate = true)
  public int id;

  @ColumnInfo(name = "TITLE")
  public String title;
  @ColumnInfo(name = "LINK")
  public String link;
  @ColumnInfo(name = "DESC")
  public String description;
}