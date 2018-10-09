package io.github.ovso.healthcare.data.network;

import io.github.ovso.healthcare.data.network.model.Car;
import io.reactivex.Single;
import java.util.List;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MainService {
  @GET("cars") Single<List<Car>> getCars(@QueryMap Map<String, Object> queryMap);

  @GET("cars") Single<List<Car>> getCars(@QueryMap Map<String, Object> queryMap,
      @Query("model") int modelId);
}
