package io.github.ovso.healthcare.data.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.google.gson.Gson;
import io.github.ovso.healthcare.utils.ResourceProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Database(entities = { WebsiteEntity.class }, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
  private final static String DATABASE_NAME = "disease";
  private static AppDatabase instance;

  public static synchronized AppDatabase getInstance(Context context) {
    if (instance == null) {
      instance = buildDatabase(context);
    }
    return instance;
  }

  private static AppDatabase buildDatabase(final Context context) {
    return Room.databaseBuilder(context,
        AppDatabase.class,
        DATABASE_NAME)
        .allowMainThreadQueries()
        .build();
  }

  public static void destroy() {
    if (instance.isOpen()) {
      instance.close();
      instance = null;
    }
  }

  public abstract WebsiteDao websiteDao();

  public boolean insertFirstRunData(Context context) {
    ResourceProvider resourceProvider = new ResourceProvider(context);
    try {
      List<WebsiteEntity> websiteEntities = new ArrayList<>();
      Gson gson = new Gson();
      String jsonString = resourceProvider.assetsToJson("news.json");
      JSONArray jsonArray = new JSONArray(jsonString);
      int size = jsonArray.length();
      for (int i = 0; i < size; i++) {
        JSONObject jsonObject = jsonArray.getJSONObject(i);
        Website
            website = gson.fromJson(jsonObject.toString(), Website.class);
        WebsiteEntity websiteEntity = WebsiteEntity.convertWebsiteToEntiry(website);
        websiteEntities.add(websiteEntity);
      }

      long seed = System.nanoTime();
      Collections.shuffle(websiteEntities, new Random(seed));

      for (int i = 0; i < size; i++) {
        WebsiteEntity websiteEntity = websiteEntities.get(i);
        websiteEntity.position = i;
        websiteDao().insert(websiteEntity);
      }

      return true;
    } catch (JSONException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
