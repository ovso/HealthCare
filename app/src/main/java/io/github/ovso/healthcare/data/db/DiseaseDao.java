package io.github.ovso.healthcare.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao public interface DiseaseDao {

  @Query("SELECT * FROM DISEASE_LIKE")
  LiveData<List<DiseaseEntity>> getLiveDataItems();

  @Query("SELECT * FROM DISEASE_LIKE")
  List<DiseaseEntity> getItems();

  //  @Query("SELECT * FROM WEBSITE_INFO WHERE ID IN (:ids)")
  //  List<DiseaseEntity> loadAllByIds(int[] ids);

  @Insert
  void insert(DiseaseEntity... entities);

  @Insert
  void insert(List<DiseaseEntity> entities);

  @Delete
  void delete(DiseaseEntity entity);

  @Update
  void update(DiseaseEntity entity);
}
