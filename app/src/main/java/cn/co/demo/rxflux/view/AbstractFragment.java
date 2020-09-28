package cn.co.demo.rxflux.view;

import android.content.Context;
import java.lang.ref.WeakReference;
import javax.inject.Inject;
import cn.co.demo.rxflux.ViewDataBinder;
import dagger.android.support.DaggerFragment;
import io.reactivex.disposables.CompositeDisposable;
import cn.co.demo.rxflux.action.StartFragmentAction;
import cn.co.demo.rxflux.actionCreator.StartFragmentActionCreator;
import cn.co.demo.rxflux.dispatcher.Dispatcher;
import cn.co.demo.rxflux.log.Log;

public abstract class AbstractFragment extends DaggerFragment implements ViewDataBinder {
  @Inject Dispatcher mDispatcher;
  @Inject
  StartFragmentActionCreator mStartFragmentActionCreator;
  protected final CompositeDisposable mDisposableWhileFragmentAttached = new CompositeDisposable();
  protected final CompositeDisposable mDisposableWhileViewAlive = new CompositeDisposable();


  @Override public void onAttach(Context context) {
    Log.d(this.getClass().getSimpleName(), "onAttach enter:" + Integer.toHexString(hashCode()));
    super.onAttach(context);
    mDispatcher.dispatch(new StartFragmentAction.OnAttachFragment(
        new StartFragmentAction.FragmentInformation(this.getClass(),
            new WeakReference<>(this))));
    Log.d(this.getClass().getSimpleName(), "onAttach exit:" + Integer.toHexString(hashCode()));
  }

  @Override public void onResume() {
    Log.d(this.getClass().getSimpleName(), "onResume enter:" + Integer.toHexString(hashCode()));
    super.onResume();
    mDispatcher.dispatch(new StartFragmentAction.OnResumeFragment(
        new StartFragmentAction.FragmentInformation(this.getClass(),
            new WeakReference<>(this))));
    Log.d(this.getClass().getSimpleName(), "onResume exit:" + Integer.toHexString(hashCode()));
  }

  @Override public void onPause() {
    Log.d(this.getClass().getSimpleName(), "onPause enter:" + Integer.toHexString(hashCode()));
    super.onPause();
    mDispatcher.dispatch(new StartFragmentAction.OnPauseFragment(
        new StartFragmentAction.FragmentInformation(this.getClass(),
            new WeakReference<>(this))));
    Log.d(this.getClass().getSimpleName(), "onPause exit:" + Integer.toHexString(hashCode()));
  }

  @Override public void onDestroyView() {
    Log.d(this.getClass().getSimpleName(), "onDestroyView enter:" + Integer.toHexString(hashCode()));
    super.onDestroyView();
    mDisposableWhileViewAlive.clear();
    mDispatcher.dispatch(new StartFragmentAction.OnDestroyViewFragment(
        new StartFragmentAction.FragmentInformation(this.getClass(),
            new WeakReference<>(this))));
    Log.d(this.getClass().getSimpleName(), "onDestroyView exit:" + Integer.toHexString(hashCode()));
  }

  @Override public void onDetach() {
    Log.d(this.getClass().getSimpleName(), "onDetach enter:" + Integer.toHexString(hashCode()));
    super.onDetach();
    mDisposableWhileFragmentAttached.clear();
    mDispatcher.dispatch(new StartFragmentAction.OnDetachFragment(
        new StartFragmentAction.FragmentInformation(this.getClass(),
            new WeakReference<>(this))));
    Log.d(this.getClass().getSimpleName(), "onDetach exit:" + Integer.toHexString(hashCode()));
  }

  @Override protected void finalize() throws Throwable {
    super.finalize();
    Log.v(this.getClass().getSimpleName(), "finalize:" + Integer.toHexString(hashCode()));
  }

}
