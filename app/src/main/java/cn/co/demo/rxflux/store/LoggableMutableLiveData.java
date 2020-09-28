package cn.co.demo.rxflux.store;

import androidx.lifecycle.MutableLiveData;
import cn.co.demo.rxflux.log.Log;

public class LoggableMutableLiveData<T> extends MutableLiveData<T> {
  private final String mVariableName;

  public LoggableMutableLiveData(final String variableName) {
    super();
    mVariableName = variableName;
  }

  public LoggableMutableLiveData(final String variableName, final T initialValue) {
    this(variableName);
    postValue(initialValue);
  }

  @Override public void postValue(T value) {
    super.postValue(value);
    Log.v(mVariableName, "postValue value:" + value);
  }

  @Override public void setValue(T value) {
    super.setValue(value);
    Log.v(mVariableName, "setValue value:" + value);
  }

  @Override protected void onActive() {
    super.onActive();
    Log.v(mVariableName, "onActive");
  }

  @Override protected void onInactive() {
    super.onInactive();
    Log.v(mVariableName, "onInactive");
  }
}
