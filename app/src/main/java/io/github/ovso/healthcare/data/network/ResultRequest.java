package io.github.ovso.healthcare.data.network;

import android.text.TextUtils;
import io.github.ovso.healthcare.Security;
import io.github.ovso.healthcare.data.KeyName;
import io.github.ovso.healthcare.data.network.model.youtube.Search;
import io.reactivex.Single;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import okhttp3.Headers;

public class ResultRequest extends BaseRequest<ResultService> {

  @Inject
  public ResultRequest() {

  }

  @Override protected Class getApiClass() {
    return ResultService.class;
  }

  @Override protected Headers createHeaders() {
    return new Headers.Builder().build();
  }

  @Override protected String getBaseUrl() {
    return EndPoint.RESULT;
  }

  public Single<Search> getResult(String q, String pageToken) {
    return getApi().getResult(createQueryMap(q, pageToken));
  }

  private Map<String, Object> createQueryMap(String q, String pageToken) {
    Map<String, Object> queryMap = new HashMap<>();
    queryMap.put(KeyName.Q, q);
    queryMap.put(KeyName.MAX_RESULTS, 50);
    queryMap.put(KeyName.ORDER, "date");
    queryMap.put(KeyName.TYPE, "video");
    queryMap.put(KeyName.VIDEO_SYNDICATED, "false");
    queryMap.put(KeyName.KEY, Security.KEY.getValue());
    queryMap.put(KeyName.PART, "snippet");
    if (!TextUtils.isEmpty(pageToken)) {
      queryMap.put(KeyName.PAGE_TOKEN, pageToken);
    }
    return queryMap;
  }
}