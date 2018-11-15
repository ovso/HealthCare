package io.github.ovso.healthcare.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao public interface WebsiteDao {

  @Query("SELECT * FROM WEBSITE_INFO")
  LiveData<List<WebsiteEntity>> getLiveDataItems();

  @Query("SELECT * FROM WEBSITE_INFO")
  List<WebsiteEntity> getItems();

//  @Query("SELECT * FROM WEBSITE_INFO WHERE ID IN (:ids)")
//  List<WebsiteEntity> loadAllByIds(int[] ids);

  @Insert
  void insert(WebsiteEntity website);

  @Delete
  void delete(WebsiteEntity website);

  @Update
  void update(WebsiteEntity website);
}
