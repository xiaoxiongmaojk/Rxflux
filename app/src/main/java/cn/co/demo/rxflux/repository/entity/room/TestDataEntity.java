package cn.co.demo.rxflux.repository.entity.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "test_data", primaryKeys = {"name","sex"})
public class TestDataEntity {

  @NonNull
  @ColumnInfo(name = "name")
  private String mName;

  @NonNull
  @ColumnInfo(name = "sex")
  private String mSex;

  @ColumnInfo(name = "age")
  private int mAge;

  @ColumnInfo(name = "country")
  private String mCountry;

  @NonNull
  public String getName() {
    return mName;
  }

  public void setName(@NonNull String name) {
    this.mName = name;
  }

  @NonNull
  public String getSex() {
    return mSex;
  }

  public void setSex(@NonNull String sex) {
    this.mSex = sex;
  }

  public int getAge() {
    return mAge;
  }

  public void setAge(int age) {
    this.mAge = age;
  }

  public String getCountry() {
    return mCountry;
  }

  public void setCountry(String country) {
    this.mCountry = country;
  }
}
