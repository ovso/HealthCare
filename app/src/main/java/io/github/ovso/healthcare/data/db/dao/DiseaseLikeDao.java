package io.github.ovso.healthcare.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import io.github.ovso.healthcare.data.db.model.DiseaseLikeEntity;
import java.util.List;

@Dao public interface DiseaseLikeDao {

  @Query("SELECT * FROM DISEASE_INFO")
  LiveData<List<DiseaseLikeEntity>> getLiveItems();

  @Query("SELECT * FROM DISEASE_INFO")
  List<DiseaseLikeEntity> getItems();

  @Insert
  void insert(DiseaseLikeEntity... entities);

  @Insert
  void insert(List<DiseaseLikeEntity> entities);

  @Delete
  void delete(DiseaseLikeEntity entity);

  @Update
  void update(DiseaseLikeEntity entity);
}
