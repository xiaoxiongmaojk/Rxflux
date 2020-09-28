package cn.co.demo.rxflux.repository.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * エラー情報を持っているオブジェクトです。
 */
public class ErrorInfo implements Serializable {

  @SerializedName("errorCode")
  @Expose
  String code;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  /**
   * デバッグ用のtoString関数。
   *
   * @return Stringオブジェクト
   */
  @Override public String toString() {
    return "ErrorInfo{" +
        "code='" + code + '\'' +
        '}';
  }
}