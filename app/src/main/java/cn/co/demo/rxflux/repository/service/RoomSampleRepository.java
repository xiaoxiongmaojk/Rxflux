package cn.co.demo.rxflux.repository.service;

import android.app.Application;

import java.util.List;

import javax.inject.Inject;

import cn.co.demo.rxflux.di.PerApplicationScope;
import cn.co.demo.rxflux.repository.entity.dao.TestDataDao;
import cn.co.demo.rxflux.repository.entity.database.Database;
import cn.co.demo.rxflux.repository.entity.room.TestDataEntity;
import io.reactivex.Single;

@PerApplicationScope
public class RoomSampleRepository {
  private TestDataDao mTestDataDao;

  @Inject
  public RoomSampleRepository(Application application) {
    Database database = Database.getDatabase(application.getApplicationContext());
    mTestDataDao = database.getTestDataDao();
  }

  public void insert(TestDataEntity... testDataEntities){
    mTestDataDao.insert(testDataEntities);
  }

  public void update(TestDataEntity... testDataEntities){
    mTestDataDao.update(testDataEntities);
  }

  public void delete(TestDataEntity... testDataEntities){
    mTestDataDao.delete(testDataEntities);
  }

  public void deleteAll(){
    mTestDataDao.deleteAll();
  }

  public Single<List<TestDataEntity>> getAllTestDataEntity(){
    return mTestDataDao.getAllTestDataEntity();
  }
}
