package cn.co.demo.rxflux.repository.entity.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import cn.co.demo.rxflux.repository.entity.dao.TestDataDao;
import cn.co.demo.rxflux.repository.entity.room.TestDataEntity;

@androidx.room.Database(entities = TestDataEntity.class, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
  private static volatile Database INSTANCE;

  public static synchronized Database getDatabase(Context context){
    if (INSTANCE == null) {
      synchronized (Database.class){
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
              Database.class,
              "Database.db")
              .build();
        }
      }
    }
    return INSTANCE;
  }

  public abstract TestDataDao getTestDataDao();
}
