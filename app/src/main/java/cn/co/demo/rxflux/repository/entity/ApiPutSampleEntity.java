package cn.co.demo.rxflux.repository.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApiPutSampleEntity implements Serializable {

  @SerializedName("gigyaUuid")
  @Expose(deserialize = false)
  private String gigyaUuid;

  @SerializedName("ccuId")
  @Expose(deserialize = false)
  private String ccuId;

  @SerializedName("oilJudgementMode")
  @Expose
  private String mOilJudgementMode;

  @SerializedName("oilJudgementDistance")
  @Expose
  private Number mOilJudgementDistance;

  @SerializedName("oilJudgementDays")
  @Expose
  private Number mOilJudgementDays;

  public void setGigyaUuid(String gigyaUuid) {
    this.gigyaUuid = gigyaUuid;
  }

  public void setCcuId(String ccuId) {
    this.ccuId = ccuId;
  }

  public void setOilJudgementMode(String mode) {
    this.mOilJudgementMode = mode;
  }

  public void setOilJudgementDistance(Number oilJudgementDistance) {
    this.mOilJudgementDistance = oilJudgementDistance;
  }

  public void setOilJudgementDays(Number oilJudgementDays) {
    this.mOilJudgementDays = oilJudgementDays;
  }

  public String getOilJudgementMode() {
    return mOilJudgementMode;
  }

  public Number getOilJudgementDistance() {
    return mOilJudgementDistance;
  }

  public Number getOilJudgementDays() {
    return mOilJudgementDays;
  }

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
