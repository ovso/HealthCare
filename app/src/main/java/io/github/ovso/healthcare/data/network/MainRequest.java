package io.github.ovso.healthcare.data.network;

import io.github.ovso.healthcare.data.network.model.Car;
import io.reactivex.Single;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import okhttp3.Headers;

public class MainRequest extends BaseRequest<MainService> {

  @Inject
  public MainRequest() {

  }

  @Override protected Class getApiClass() {
    return MainService.class;
  }

  @Override protected Headers createHeaders() {
    return new Headers.Builder().build();
  }

  @Override protected String getBaseUrl() {
    return null;
  }

  public Single<List<Car>> getCars(int page) {
    Map<String, Object> queryMap = new HashMap<>();
    queryMap.put("format", "json");
    queryMap.put("page", page);
    return getApi().getCars(queryMap);
  }

  public Single<List<Car>> getCars(int page, int modelId) {
    Map<String, Object> queryMap = new HashMap<>();
    queryMap.put("format", "json");
    queryMap.put("page", page);
    return getApi().getCars(queryMap, modelId);
  }
}