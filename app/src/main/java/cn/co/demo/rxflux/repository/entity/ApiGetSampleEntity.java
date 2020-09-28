package cn.co.demo.rxflux.repository.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApiGetSampleEntity implements Serializable {

  @SerializedName("modelCd")//重命名
  @Expose(serialize = true, deserialize = true)//是否需要序列化与反序列化
  private String mModelCd;

  public String getModelCd() {
    return mModelCd;
  }
}
