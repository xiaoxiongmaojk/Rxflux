package cn.co.demo.rxflux.actionCreator;

import javax.inject.Inject;

import cn.co.demo.rxflux.action.RoomSampleAction;
import cn.co.demo.rxflux.di.PerApplicationScope;
import cn.co.demo.rxflux.dispatcher.Dispatcher;
import cn.co.demo.rxflux.log.Log;
import cn.co.demo.rxflux.repository.entity.room.TestDataEntity;
import cn.co.demo.rxflux.repository.service.RoomSampleRepository;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@PerApplicationScope
public class RoomSampleActionCreator {

  private static final String TAG = RoomSampleActionCreator.class.getSimpleName();
  private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
  private Dispatcher mDispatcher;
  private RoomSampleRepository mRoomSampleRepository;

  @Inject
  public RoomSampleActionCreator(final Dispatcher dispatcher,
                                 final RoomSampleRepository roomSampleRepository) {
    mDispatcher = dispatcher;
    mRoomSampleRepository = roomSampleRepository;
  }

  public void doInsert(TestDataEntity[] testDataEntities){
//    if (testDataEntities != null) {
//      mRoomSampleRepository.insert(testDataEntities);
//    }
    TestDataEntity mTestDataEntity = new TestDataEntity();
    mTestDataEntity.setName("aaa");
    mTestDataEntity.setSex("F");
    mTestDataEntity.setAge(20);
    mTestDataEntity.setCountry("China");
    mRoomSampleRepository.insert(mTestDataEntity);
    Log.d(TAG, "#11011 doInsert");
  }

  public void doUpdate(TestDataEntity... testDataEntities){
    if (testDataEntities != null) {
      mRoomSampleRepository.update(testDataEntities);
      Log.d(TAG, "#11011 doUpdate");
    }
  }

  public void doDelete(TestDataEntity... testDataEntities){
    if (testDataEntities != null) {
      mRoomSampleRepository.delete(testDataEntities);
      Log.d(TAG, "#11011 doDelete");
    }
  }

  public void doDeleteAll(){
    mRoomSampleRepository.deleteAll();
    Log.d(TAG, "#11011 doDeleteAll");
  }

  public void doGetAllTestDataEntity(){
    mCompositeDisposable.add(
        mRoomSampleRepository.getAllTestDataEntity()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .filter(action -> !action.isEmpty())
            .map(action -> action.get(0))
            .subscribe(action -> {
              Log.d(TAG, "#11011 doGetAllTestDataEntity");
              mDispatcher.dispatch(new RoomSampleAction.OnGetAllRoomSampleData(action));
            })
    );
  }
}
