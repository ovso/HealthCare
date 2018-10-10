package io.github.ovso.healthcare.data.network;

import io.github.ovso.healthcare.data.network.model.SearchItem;
import io.reactivex.Single;
import java.util.List;
import retrofit2.http.GET;

public interface ResultService {
  @GET("car_meta/brands") Single<List<SearchItem>> getBrands();
}
