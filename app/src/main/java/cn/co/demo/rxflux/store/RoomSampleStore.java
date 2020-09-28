package cn.co.demo.rxflux.store;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cn.co.demo.rxflux.ViewDataBindee;
import cn.co.demo.rxflux.action.RoomSampleAction;
import cn.co.demo.rxflux.actionCreator.RoomSampleActionCreator;
import cn.co.demo.rxflux.di.PerApplicationScope;
import cn.co.demo.rxflux.dispatcher.Dispatcher;
import cn.co.demo.rxflux.log.Log;
import cn.co.demo.rxflux.repository.entity.room.TestDataEntity;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@PerApplicationScope
public class RoomSampleStore extends ViewModel implements ViewDataBindee {
  private static final String TAG = RoomSampleStore.class.getSimpleName();
  private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
  private final MutableLiveData<String> mGetRoomSampleData = new LoggableMutableLiveData<>("mGetRoomSampleData");
  @Inject
  RoomSampleActionCreator mRoomSampleActionCreator;


  @Inject
  public RoomSampleStore(final Application application, final Dispatcher dispatcher) {
    mCompositeDisposable.add(
        dispatcher.on(RoomSampleAction.OnRoomSampleInsert.TYPE)
            .observeOn(Schedulers.io())
            .subscribe(action -> {
              Log.d(TAG, "#11011 doInsert");
              mRoomSampleActionCreator.doInsert(null);
              }));
    mCompositeDisposable.add(
        dispatcher.on(RoomSampleAction.OnGetAllRoomSampleData.TYPE)
            .map(action -> (TestDataEntity) action.getData())
            .subscribe(testDataEntity -> {
              onGetRoomSampleData(testDataEntity);
              Log.d(TAG, "#11011 onGetRoomSampleData");
            }));
  }

  private void onGetRoomSampleData(@NonNull final TestDataEntity testDataEntity) {
    mGetRoomSampleData.postValue(
        testDataEntity.getName() + ", " +
        testDataEntity.getSex() + ", " +
        testDataEntity.getAge() + ", " +
        testDataEntity.getCountry());
  }

  public LiveData<String> getRoomSampleData() {
    return mGetRoomSampleData;
  }
}
