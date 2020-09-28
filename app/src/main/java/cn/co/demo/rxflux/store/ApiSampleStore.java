package cn.co.demo.rxflux.store;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cn.co.demo.rxflux.ViewDataBindee;
import cn.co.demo.rxflux.action.ApiSampleAction;
import cn.co.demo.rxflux.di.PerApplicationScope;
import cn.co.demo.rxflux.dispatcher.Dispatcher;
import cn.co.demo.rxflux.repository.entity.ApiGetSampleEntity;
import io.reactivex.disposables.CompositeDisposable;

@PerApplicationScope
public class ApiSampleStore extends ViewModel implements ViewDataBindee {
  private static final String TAG = ApiSampleStore.class.getSimpleName();
  private final MutableLiveData<String> mApiGetSampleData = new LoggableMutableLiveData<>("mApiGetData");
  private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

  @Inject
  public ApiSampleStore(final Application application, final Dispatcher dispatcher) {
    mCompositeDisposable.add(
        dispatcher.on(ApiSampleAction.OnApiGetSample.TYPE)
            .map(action -> (ApiGetSampleEntity) action.getData())
            .subscribe(this::onApiGetSampleData));
  }

  private void onApiGetSampleData(@NonNull final ApiGetSampleEntity apiGetSampleEntity) {
    mApiGetSampleData.postValue(apiGetSampleEntity.getModelCd());
  }

  public LiveData<String> getApiGetSampleData() {
    return mApiGetSampleData;
  }
}
