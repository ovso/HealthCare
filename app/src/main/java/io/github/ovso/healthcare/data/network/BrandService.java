package io.github.ovso.healthcare.data.network;

import io.github.ovso.healthcare.data.network.model.Brand;
import io.reactivex.Single;
import java.util.List;
import retrofit2.http.GET;

public interface BrandService {
  @GET("car_meta/brands") Single<List<Brand>> getBrands();
}
