package io.github.ovso.healthcare.data.network;

import io.github.ovso.healthcare.data.network.model.ModelGroupDetail;
import io.reactivex.Single;
import javax.inject.Inject;
import okhttp3.Headers;

public class ModelRequest extends BaseRequest<ModelService> {

  @Inject
  public ModelRequest() {

  }

  @Override protected Class getApiClass() {
    return ModelService.class;
  }

  @Override protected Headers createHeaders() {
    return new Headers.Builder().build();
  }

  @Override protected String getBaseUrl() {
    return null;
  }

  public Single<ModelGroupDetail> getModel(int id) {
    return getApi().getModel(id);
  }
}