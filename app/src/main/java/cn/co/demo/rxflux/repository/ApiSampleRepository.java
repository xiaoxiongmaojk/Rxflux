package cn.co.demo.rxflux.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Size;

import javax.inject.Inject;

import cn.co.demo.rxflux.repository.entity.ApiDeleteSampleEntity;
import cn.co.demo.rxflux.repository.entity.ApiPostSampleEntity;
import cn.co.demo.rxflux.repository.entity.ApiPutSampleEntity;
import io.reactivex.Completable;
import io.reactivex.Single;
import cn.co.demo.rxflux.di.PerApplicationScope;
import cn.co.demo.rxflux.log.Log;
import cn.co.demo.rxflux.repository.entity.ApiGetSampleEntity;
import cn.co.demo.rxflux.repository.service.ApiSampleService;

@PerApplicationScope
public class ApiSampleRepository extends CloudApiAccessRepository {
  private static final String TAG = ApiSampleRepository.class.getSimpleName();
  private final ApiSampleService mApiSampleService;

  @Inject
  public ApiSampleRepository(final Application application) {
    mApiSampleService =
            createService(application, ApiSampleService.class);
  }

  //Single<ApiGetSampleEntity>可以定义返回的数据类型
  public Single<ApiGetSampleEntity> doApiGetSample(
          @NonNull @Size(min = 1) String ccuId) {
    Log.d(TAG, "doApiGetSample");
    //测试数据
    String testData = "00001111999906";
    //TODO 对象序列化，java对象接收数据
    return mApiSampleService.ApiGetSampleData(testData);
  }

  //Completable默认返回的数据类型onComplete or onError
  public Completable doApiPostSample(
          @NonNull final ApiPostSampleEntity apiPostSampleEntity) {
    Log.d(TAG, "doApiPostSample");
    //测试数据
    ApiPostSampleEntity mApiPostSampleEntity = new ApiPostSampleEntity();
    mApiPostSampleEntity.setGigyaUuid("0580b706b0ef461281ee4d6a7d57ab0b");
    mApiPostSampleEntity.setCcuId("00001111999906");
    mApiPostSampleEntity.setActualJudgeBattery("1");
    mApiPostSampleEntity.setTempJudgeBattery("11");
    return mApiSampleService.apiPostSample(mApiPostSampleEntity);
  }

  public Completable doApiPutSample(@NonNull ApiPutSampleEntity apiPutSampleEntity) {
    Log.d(TAG, "doApiPutSample");
    //测试数据
    ApiPutSampleEntity mApiPutSampleEntity = new ApiPutSampleEntity();
    mApiPutSampleEntity.setGigyaUuid("0580b706b0ef461281ee4d6a7d57ab0b");
    mApiPutSampleEntity.setCcuId("00001111999906");
    mApiPutSampleEntity.setOilJudgementMode("1");
    mApiPutSampleEntity.setOilJudgementDistance(123);
    return mApiSampleService.apiPutSample(mApiPutSampleEntity);
  }

  public Completable doApiDeleteSample(@NonNull final ApiDeleteSampleEntity apiDeleteSampleEntity) {
    Log.d(TAG, "doApiDeleteSample");
    //测试数据
    ApiDeleteSampleEntity mApiDeleteSampleEntity = new ApiDeleteSampleEntity();
    ApiDeleteSampleEntity.ResetHistory resetHistory = new ApiDeleteSampleEntity.ResetHistory();
    resetHistory.setResetTarget("1");
    resetHistory.setResetDateTz("2020-03-12T12:11:40.294+09:00");
    mApiDeleteSampleEntity.getResetHistory().add(resetHistory);
    mApiDeleteSampleEntity.setCcuId("00001111999906");
    return mApiSampleService.apiDeleteSample(mApiDeleteSampleEntity);
  }
}
