package cn.co.demo.rxflux.repository.entity.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cn.co.demo.rxflux.repository.entity.room.TestDataEntity;
import io.reactivex.Single;

@Dao
public interface TestDataDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(TestDataEntity... testDataEntity);

  @Update(onConflict = OnConflictStrategy.REPLACE)
  void update(TestDataEntity... testDataEntity);

  @Delete
  void delete(TestDataEntity... testDataEntity);

  @Query("delete from test_data")
  void deleteAll();

  @Query("select * from test_data")
  Single<List<TestDataEntity>> getAllTestDataEntity();
}
