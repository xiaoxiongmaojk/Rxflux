package cn.co.demo.rxflux.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;
import cn.co.demo.rxflux.ViewDataBinder;
import cn.co.demo.rxflux.databinding.SampleFragmentBinding;
import cn.co.demo.rxflux.store.ApiSampleStore;
import cn.co.demo.rxflux.store.SampleStore;

public class SampleFragment extends AbstractFragment {
  @Inject
  SampleStore mSampleStore;
  @Inject
  ApiSampleStore mApiSampleStore;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    final SampleFragmentBinding sampleFragmentBinding =
            SampleFragmentBinding.inflate(inflater, container, false);
    sampleFragmentBinding.setLifecycleOwner(this);
    ViewDataBinder.setViewDataBindings(sampleFragmentBinding, this);
    return sampleFragmentBinding.getRoot();
  }
}
