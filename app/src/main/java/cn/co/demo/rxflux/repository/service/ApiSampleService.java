package cn.co.demo.rxflux.repository.service;

import cn.co.demo.rxflux.repository.entity.ApiDeleteSampleEntity;
import cn.co.demo.rxflux.repository.entity.ApiPostSampleEntity;
import cn.co.demo.rxflux.repository.entity.ApiPutSampleEntity;
import io.reactivex.Completable;
import io.reactivex.Single;
import cn.co.demo.rxflux.repository.entity.ApiGetSampleEntity;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiSampleService {

  /**
   * HTTP GET请求
   */
  @GET("vehicleinfo/{ccuId}")
  Single<ApiGetSampleEntity> ApiGetSampleData(@Path("ccuId") String ccuId);

  /**
   * HTTP POST请求
   * 新建数据库记录时推荐使用post
   */
  @POST("maintereco_battery")
  @Headers({"Content-Type: application/json;charset=UTF-8"})
  Completable apiPostSample(@Body ApiPostSampleEntity apiPostSampleEntity);

  /**
   * HTTP PUT请求
   * 更新数据库记录时推荐使用put
   */
  @PUT("maintereco_settinginfo")
  Completable apiPutSample(@Body ApiPutSampleEntity apiPutSampleEntity);

  /**
   * HTTP DELETE请求
   */
  @HTTP(method = "DELETE", path = "maintereco_reset_history", hasBody = true)
  Completable apiDeleteSample(@Body ApiDeleteSampleEntity apiDeleteSampleEntity);
}
