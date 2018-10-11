package io.github.ovso.healthcare.data.network;

import io.github.ovso.healthcare.data.network.model.BrandGroup;
import io.reactivex.Single;
import javax.inject.Inject;
import okhttp3.Headers;

public class ModelGroupRequest extends BaseRequest<ModelGroupService> {

  @Inject
  public ModelGroupRequest() {

  }

  @Override protected Class getApiClass() {
    return ModelGroupService.class;
  }

  @Override protected Headers createHeaders() {
    return new Headers.Builder().build();
  }

  @Override protected String getBaseUrl() {
    return null;
  }

  public Single<BrandGroup> getBrandDetail(int id) {
    return getApi().getBrandDetail(id);
  }
}