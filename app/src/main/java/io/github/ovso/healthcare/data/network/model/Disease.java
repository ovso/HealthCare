package io.github.ovso.healthcare.data.network.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import lombok.Getter;

@Getter public class Disease {
  private String code;
  private String name;
  private boolean like;

  public static List<Disease> fromJson(String json) {
    Gson gson = new Gson();
    Type type = new TypeToken<List<Disease>>() {
    }.getType();
    return gson.fromJson(json, type);
  }
}