package cn.co.demo.rxflux.store;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cn.co.demo.rxflux.di.PerApplicationScope;
import cn.co.demo.rxflux.ViewDataBindee;

@PerApplicationScope
public class SampleStore extends ViewModel implements ViewDataBindee {
  private final MutableLiveData<String> mText = new LoggableMutableLiveData<>("mText");

  @Inject
  public SampleStore() {

  }

  public void setText(String text) {
    mText.postValue(text);
  }

  public LiveData<String> getText() {
    return mText;
  }
}
