package cn.co.demo.rxflux.di.applicaton;

import cn.co.demo.rxflux.view.RxfluxMainActivity;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface RxfluxMainActivityComponent extends AndroidInjector<RxfluxMainActivity> {
  @Subcomponent.Builder
  abstract class Builder extends AndroidInjector.Builder<RxfluxMainActivity> {
  }
}
