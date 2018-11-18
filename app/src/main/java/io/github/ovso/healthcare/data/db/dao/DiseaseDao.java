package io.github.ovso.healthcare.data.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import io.github.ovso.healthcare.data.db.model.DiseaseEntity;
import java.util.List;

@Dao public interface DiseaseDao {

  @Query("SELECT * FROM DISEASE_INFO")
  LiveData<List<DiseaseEntity>> getLiveItems();

  @Query("SELECT * FROM DISEASE_INFO")
  LiveData<DiseaseEntity> getLiveItem();

  @Query("SELECT * FROM DISEASE_INFO")
  List<DiseaseEntity> getItems();

  @Query("SELECT * FROM DISEASE_INFO WHERE LIKED = 1")
  LiveData<List<DiseaseEntity>> getLikeLiveItems();

  @Insert
  void insert(DiseaseEntity... entities);

  @Insert
  void insert(List<DiseaseEntity> entities);

  @Delete
  void delete(DiseaseEntity entity);

  @Update
  void update(DiseaseEntity entity);
}
