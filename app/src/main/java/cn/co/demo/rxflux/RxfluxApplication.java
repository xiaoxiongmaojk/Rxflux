package cn.co.demo.rxflux;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Field;

import cn.co.demo.rxflux.di.DaggerRxfluxApplicationComponent;
import cn.co.demo.rxflux.log.Log;
import cn.co.demo.rxflux.log.LogcatLogger;
import cn.co.demo.rxflux.store.LoggableMutableLiveData;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import io.reactivex.disposables.CompositeDisposable;

public class RxfluxApplication extends DaggerApplication implements LifecycleOwner {
  static {
    Log.addLogger(new LogcatLogger());
  }
//
//  @Inject
//  SendTextStore mSendTextStore;
  private static final String TAG = RxfluxApplication.class.getSimpleName();
  private final CompositeDisposable mCompositeDisposable = new CompositeDisposable();
  private LifecycleRegistry mLifecycleRegistry;
  @Override public void onCreate() {
    mLifecycleRegistry = new LifecycleRegistry(this);
    mLifecycleRegistry.markState(Lifecycle.State.CREATED);
    mLifecycleRegistry.markState(Lifecycle.State.STARTED);
    mLifecycleRegistry.markState(Lifecycle.State.RESUMED);
    super.onCreate();
//    observeForever(mSendTextStore);
  }

  @SuppressWarnings("unchecked")
  private static void observeForever(final ViewModel viewModel) {
    for (final Field each : viewModel.getClass().getDeclaredFields()) {
      try {
        each.setAccessible(true);
        final Object object = each.get(viewModel);
        if (object instanceof LoggableMutableLiveData) {
          ((LoggableMutableLiveData) object).observeForever(o -> {
            // LoggableMutableLiveDataでログ出力されているのでここでは何もしない
          });
        } else if (object instanceof LiveData) {
          ((LiveData) object).observeForever(o -> {
            Log.v(each.getName(), "onChanged t:" + o);
          });
        }
      } catch (ReflectiveOperationException e) {
        Log.d(RxfluxApplication.class.getSimpleName(), "observeForever", e);
      }
    }
  }

  @Override protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    AndroidInjector<RxfluxApplication> androidInjector =
            DaggerRxfluxApplicationComponent.builder().create(this);
    androidInjector.inject(this);
    return androidInjector;
  }

  @Override
  public void onTerminate() {
    super.onTerminate();
    mCompositeDisposable.clear();
    mLifecycleRegistry.markState(Lifecycle.State.DESTROYED);
  }

  @NonNull @Override
  public Lifecycle getLifecycle() {
    return mLifecycleRegistry;
  }

}
