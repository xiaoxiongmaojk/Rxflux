package cn.co.demo.rxflux.di;

import cn.co.demo.rxflux.RxfluxApplication;
import cn.co.demo.rxflux.di.applicaton.SampleFragmentModule;
import cn.co.demo.rxflux.di.applicaton.RxfluxMainActivityModule;
import cn.co.demo.rxflux.di.applicaton.SecondFragmentModule;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@PerApplicationScope
@Component(modules = {
        AndroidSupportInjectionModule.class,
        RxfluxApplicationModule.class,
        RxfluxMainActivityModule.class,
        SampleFragmentModule.class,
        SecondFragmentModule.class
})
public interface RxfluxApplicationComponent extends AndroidInjector<RxfluxApplication> {

  @Component.Builder
  abstract class Builder extends AndroidInjector.Builder<RxfluxApplication> {
  }
}
