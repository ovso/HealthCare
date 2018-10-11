package io.github.ovso.healthcare.data.network;

import io.github.ovso.healthcare.data.network.model.SearchItem;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;
import okhttp3.Headers;

public class BrandRequest extends BaseRequest<BrandService> {

  @Inject
  public BrandRequest() {

  }

  @Override protected Class getApiClass() {
    return BrandService.class;
  }

  @Override protected Headers createHeaders() {
    return new Headers.Builder().build();
  }

  @Override protected String getBaseUrl() {
    return null;
  }

  public Single<List<SearchItem>> getBrands() {
    return getApi().getBrands();
  }
}