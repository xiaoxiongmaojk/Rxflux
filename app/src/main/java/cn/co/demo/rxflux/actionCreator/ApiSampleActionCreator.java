package cn.co.demo.rxflux.actionCreator;


import androidx.annotation.NonNull;
import androidx.annotation.Size;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import cn.co.demo.rxflux.repository.ApiSampleRepository;
import cn.co.demo.rxflux.repository.entity.ApiDeleteSampleEntity;
import cn.co.demo.rxflux.repository.entity.ApiPostSampleEntity;
import cn.co.demo.rxflux.repository.entity.ApiPutSampleEntity;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import cn.co.demo.rxflux.action.ApiSampleAction;
import cn.co.demo.rxflux.di.PerApplicationScope;
import cn.co.demo.rxflux.dispatcher.Dispatcher;
import cn.co.demo.rxflux.log.Log;

@PerApplicationScope
public class ApiSampleActionCreator {
  private static final String TAG =
      ApiSampleActionCreator.class.getSimpleName();
  private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
  private final ApiSampleRepository mApiSampleRepository;
  private final Dispatcher mDispatcher;

  @Inject
  public ApiSampleActionCreator(final Dispatcher dispatcher,
                                final ApiSampleRepository apiSampleRepository) {
    mDispatcher = dispatcher;
    mApiSampleRepository = apiSampleRepository;
  }

  public void apiGetSample(
          @NonNull @Size(min = 1) String ccuId) {
    Log.d(TAG, "apiGetSample enter");
    mCompositeDisposable.add(
            mApiSampleRepository.doApiGetSample(ccuId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .timeout(30, TimeUnit.SECONDS)
                    .retry(3)
                    .subscribe(apiGetSampleEntity -> mDispatcher.dispatch(
                            new ApiSampleAction.OnApiGetSample(apiGetSampleEntity)),
                            throwable -> Log.w(TAG, "apiGetSample onError", throwable)));
    Log.d(TAG, "apiGetSample exit");
  }

  public void apiPostSample(@NonNull ApiPostSampleEntity apiPostSampleEntity) {
    Log.v(TAG, "apiPostSample enter");
    mCompositeDisposable.add(
            mApiSampleRepository.doApiPostSample(apiPostSampleEntity)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .timeout(30, TimeUnit.SECONDS)
                    .retry(3)
                    .subscribe(() -> Log.v(TAG, "apiPostSample onComplete"),
                            throwable -> Log.e(TAG, "apiPostSample onError", throwable)
                    ));
  }

  public void apiPutSample(@NonNull final ApiPutSampleEntity apiPutSampleEntity) {
    Log.v(TAG, "apiPutSample enter");
    mCompositeDisposable.add(
            mApiSampleRepository.doApiPutSample(apiPutSampleEntity)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .timeout(30, TimeUnit.SECONDS)
                    .retry(3)
                    .subscribe(() -> Log.v(TAG, "apiPutSample onComplete"),
                            throwable -> Log.e(TAG, "apiPutSample onError", throwable)
                    ));
  }


  public void apiDeleteSample(ApiDeleteSampleEntity apiDeleteSampleEntity) {
    Log.v(TAG, "apiDeleteSample enter");
    mCompositeDisposable.add(
            mApiSampleRepository.doApiDeleteSample(apiDeleteSampleEntity)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .timeout(30, TimeUnit.SECONDS)
                    .retry(3)
                    .subscribe(() -> Log.v(TAG, "apiDeleteSample onComplete"),
                            throwable -> {
                              Log.e(TAG, "apiDeleteSample onError", throwable);
                            }
                    ));
  }
}
