package cn.co.demo.rxflux.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import cn.co.demo.rxflux.ViewDataBinder;
import cn.co.demo.rxflux.action.RoomSampleAction;
import cn.co.demo.rxflux.actionCreator.ApiSampleActionCreator;
import cn.co.demo.rxflux.actionCreator.RoomSampleActionCreator;
import cn.co.demo.rxflux.databinding.SecondFragmentBinding;
import cn.co.demo.rxflux.dispatcher.Dispatcher;
import cn.co.demo.rxflux.log.Log;
import cn.co.demo.rxflux.repository.entity.ApiDeleteSampleEntity;
import cn.co.demo.rxflux.repository.entity.ApiPostSampleEntity;
import cn.co.demo.rxflux.repository.entity.ApiPutSampleEntity;
import cn.co.demo.rxflux.store.ApiSampleStore;
import cn.co.demo.rxflux.store.RoomSampleStore;


public class SecondFragment extends AbstractFragment {
  private static final String TAG = RoomSampleStore.class.getSimpleName();

  @Inject
  ApiSampleActionCreator mApiSampleActionCreator;
  @Inject
  RoomSampleActionCreator mRoomSampleActionCreator;
  @Inject
  ApiSampleStore mApiSampleStore;
  @Inject
  RoomSampleStore mRoomSampleStore;    //TODO xml通过databinding获取Lifecircle，数据绑定，画面显示
  @Inject
  Dispatcher mDispatcher;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    final SecondFragmentBinding secondFragmentBinding =
            SecondFragmentBinding.inflate(inflater, container, false);
    secondFragmentBinding.setLifecycleOwner(this);
    ViewDataBinder.setViewDataBindings(secondFragmentBinding, this);

    getSample(null);
    postSample(null);
    putSample(null);
    deleteSample(null);

    mDispatcher.dispatch(new RoomSampleAction.OnRoomSampleInsert(null));
    getRoomSampleData();

    return secondFragmentBinding.getRoot();
  }

  private void getSample(String value) {
    mApiSampleActionCreator.apiGetSample(value);
  }

  private void postSample(ApiPostSampleEntity apiPostSampleEntity) {
    mApiSampleActionCreator.apiPostSample(apiPostSampleEntity);
  }

  private void putSample(ApiPutSampleEntity apiPutSampleEntity) {
    mApiSampleActionCreator.apiPutSample(apiPutSampleEntity);
  }

  private void deleteSample(ApiDeleteSampleEntity apiDeleteSampleEntity) {
    mApiSampleActionCreator.apiDeleteSample(apiDeleteSampleEntity);
  }

  private void getRoomSampleData() {
    Log.d("#11011", "getRoomSampleData");
    mRoomSampleActionCreator.doGetAllTestDataEntity();
  }
}