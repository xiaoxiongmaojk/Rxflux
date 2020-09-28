package cn.co.demo.rxflux.repository;

import android.content.Context;

import androidx.annotation.NonNull;

import okhttp3.Interceptor;

/* package */ abstract class CloudApiAccessRepository extends CloudAccessRepository {
  @Override protected String getUrl() {
    return API_ACCESS_URL;
  }

  @Override protected Interceptor getInterceptor(@NonNull Context context) {
    return chain -> chain.proceed(chain.request().newBuilder()
        .header("accept", "application/json")
        .header("content-type", "application/json;charset=UTF-8")
        .header("x-uid", "")
        .build());
  }
}
