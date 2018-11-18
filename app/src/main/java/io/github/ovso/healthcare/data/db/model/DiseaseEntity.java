package io.github.ovso.healthcare.data.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.ToString;

@ToString @Entity(tableName = "DISEASE_INFO") public class DiseaseEntity {
  @PrimaryKey(autoGenerate = true)
  public int id;

  @ColumnInfo(name = "NAME")
  public String name;
  @ColumnInfo(name = "CODE")
  public String code;
  @ColumnInfo(name = "LIKED")
  public boolean like;
}