package io.github.ovso.healthcare.data.network;

import io.github.ovso.healthcare.BuildConfig;
import io.github.ovso.healthcare.utils.ObjectUtils;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseRequest<T> {
  public final static int TIMEOUT_SECONDS = 7;

  public T getApi() {
    return createRetrofit().create(getApiClass());
  }

  private Retrofit createRetrofit() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(getBaseUrl())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(createClient())
        .build();

    return retrofit;
  }

  private OkHttpClient createClient() {
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    httpClient.readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS);
    httpClient.connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS);
    httpClient.addInterceptor(chain -> {
      Request original = chain.request();
      Request.Builder requestBuilder =
          original.newBuilder();

      Map<String, String> headers = createHeaders();
      if (!ObjectUtils.isEmpty(headers)) {
        for (Map.Entry<String, String> headerEntry : createHeaders().entrySet()) {
          requestBuilder.addHeader(headerEntry.getKey(), headerEntry.getValue());
        }
      }
      Request request = requestBuilder.build();
      return chain.proceed(request);
    });
    if (isInterceptor()) {
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

      httpClient.addInterceptor(interceptor);
    }
    OkHttpClient client = httpClient.build();
    return client;
  }

  protected abstract Class<T> getApiClass();

  protected abstract Map<String, String> createHeaders();

  protected abstract String getBaseUrl();

  protected boolean isInterceptor() {
    return false;
  }
}
