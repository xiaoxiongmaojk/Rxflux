package cn.co.demo.rxflux.di.applicaton;

import cn.co.demo.rxflux.view.RxfluxMainActivity;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RxfluxMainActivityModule {

  @ContributesAndroidInjector
  abstract RxfluxMainActivity contributesActivity();
}
