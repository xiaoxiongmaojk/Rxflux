package cn.co.demo.rxflux.repository.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApiDeleteSampleEntity implements Serializable {

  @SerializedName("ccuId")
  @Expose(deserialize = false)
  private String ccuId;

  @SerializedName("resetHistory")
  @Expose
  private List<ResetHistory> mResetHistory = new ArrayList<>();

  public List<ResetHistory> getResetHistory() {
    return mResetHistory;
  }

  public void setCcuId(String ccuId) {
    this.ccuId = ccuId;
  }

  public void setResetHistory(List<ResetHistory> mResetHistory) {
    this.mResetHistory = mResetHistory;
  }

  public static class ResetHistory implements Serializable {
    @SerializedName("resetDateTz")
    @Expose
    private String mResetDateTz;

    @SerializedName("distance")
    @Expose
    private Number mDistance;

    @SerializedName("resetTarget")
    @Expose(deserialize = false)
    private String mResetTarget;

    public void setResetDateTz(String resetDateTz) {
      this.mResetDateTz = resetDateTz;
    }

    public void setDistance(Number distance) {
      this.mDistance = distance;
    }

    public void setResetTarget(String resetTarget) {
      this.mResetTarget = resetTarget;
    }

    public String getResetDateTz() {
      return mResetDateTz;
    }

    public Number getDistance() {
      return mDistance;
    }

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
