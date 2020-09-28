package cn.co.demo.rxflux.di;

import android.app.Application;

import cn.co.demo.rxflux.RxfluxApplication;
import cn.co.demo.rxflux.di.applicaton.SampleFragmentComponent;
import cn.co.demo.rxflux.di.applicaton.RxfluxMainActivityComponent;
import cn.co.demo.rxflux.di.applicaton.SecondFragmentComponent;
import dagger.Binds;
import dagger.Module;

@Module(subcomponents = {
        RxfluxMainActivityComponent.class,
        SampleFragmentComponent.class,
        SecondFragmentComponent.class

})
abstract class RxfluxApplicationModule {

  @Binds
  @PerApplicationScope
  abstract Application application(RxfluxApplication application);
}
