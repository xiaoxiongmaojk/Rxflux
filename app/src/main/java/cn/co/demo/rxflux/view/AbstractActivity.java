package cn.co.demo.rxflux.view;

import android.os.Bundle;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import cn.co.demo.rxflux.action.Action;
import cn.co.demo.rxflux.action.StartFragmentAction;
import cn.co.demo.rxflux.dispatcher.Dispatcher;
import cn.co.demo.rxflux.log.Log;
import cn.co.demo.rxflux.ViewDataBinder;
import dagger.android.support.DaggerAppCompatActivity;
import io.reactivex.disposables.CompositeDisposable;

public abstract class AbstractActivity extends DaggerAppCompatActivity implements ViewDataBinder {
  @Inject
  Dispatcher mDispatcher;
  protected final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
  private final List<Action> mStartFragmentQueueList = new LinkedList<>();
  private boolean mHasSavedInstanceState = false;

  protected boolean hasSavedInstanceState() {
    return mHasSavedInstanceState;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    Log.d(this.getClass().getSimpleName(), "onCreate enter:" + Integer.toHexString(hashCode()));
    super.onCreate(savedInstanceState);
    mCompositeDisposable.add(mDispatcher.on(StartFragmentAction.StartFragment.TYPE)
            .distinctUntilChanged()
            .subscribe(this::onRequestStartFragment));
    Log.d(this.getClass().getSimpleName(), "onCreate exit:" + Integer.toHexString(hashCode()));
  }

  private void onRequestStartFragment(final Action action) {
    if (hasSavedInstanceState()) {
      mStartFragmentQueueList.add(action);
    } else {
      doStartFragment(action);
    }
  }

  protected abstract void doStartFragment(final Action action);

  @Override protected void onResume() {
    Log.d(this.getClass().getSimpleName(), "onResume enter:" + Integer.toHexString(hashCode()));
    mHasSavedInstanceState = false;
    super.onResume();
    for (final Action each : mStartFragmentQueueList) {
      doStartFragment(each);
    }
    mStartFragmentQueueList.clear();
    Log.d(this.getClass().getSimpleName(), "onResume exit:" + Integer.toHexString(hashCode()));
  }

  @Override public void onPause() {
    Log.d(this.getClass().getSimpleName(), "onPause enter:" + Integer.toHexString(hashCode()));
    mHasSavedInstanceState = true;
    super.onPause();
    Log.d(this.getClass().getSimpleName(), "onPause exit:" + Integer.toHexString(hashCode()));
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    mCompositeDisposable.clear();
    Log.v(this.getClass().getSimpleName(), "onDestroy:" + Integer.toHexString(hashCode()));
  }

  @Override protected void finalize() throws Throwable {
    super.finalize();
    Log.v(this.getClass().getSimpleName(), "finalize:" + Integer.toHexString(hashCode()));
  }
}