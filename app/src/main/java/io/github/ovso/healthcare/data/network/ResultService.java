package io.github.ovso.healthcare.data.network;

import io.github.ovso.healthcare.data.network.model.youtube.Search;
import io.reactivex.Single;
import java.util.Map;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ResultService {
  @GET("youtube/v3/search")
  Single<Search> getResult(@QueryMap Map<String, Object> queryMap);

}
