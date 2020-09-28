package cn.co.demo.rxflux.repository.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApiPostSampleEntity implements Serializable {

  @SerializedName("gigyaUuid")
  @Expose(deserialize = false)
  private String gigyaUuid;

  @SerializedName("ccuId")
  @Expose(deserialize = false)
  private String ccuId;

  @SerializedName("actualJudgeBattery")
  @Expose(deserialize = false)
  private String mActualJudgeBattery;

  @SerializedName("tempJudgeBattery")
  @Expose(deserialize = false)
  private String mTempJudgeBattery;

  public void setGigyaUuid(String gigyaUuid) {
    this.gigyaUuid = gigyaUuid;
  }

  public void setCcuId(String ccuId) {
    this.ccuId = ccuId;
  }

  public void setActualJudgeBattery(String actualJudgeBattery) {
    this.mActualJudgeBattery = actualJudgeBattery;
  }

  public void setTempJudgeBattery(String tempJudgeBattery) {
    this.mTempJudgeBattery = tempJudgeBattery;
  }

  /**
   * エラーコード
   */
  @SerializedName("errorInfo")
  @Expose
  private ErrorInfo mErrorInfo;

  public ErrorInfo getErrorInfo() {
    return mErrorInfo;
  }

  public void setErrorInfo(ErrorInfo errorInfo) {
    mErrorInfo = errorInfo;
  }
}
