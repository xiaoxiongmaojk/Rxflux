package cn.co.demo.rxflux.di.applicaton;

import cn.co.demo.rxflux.view.SecondFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SecondFragmentModule {

  @ContributesAndroidInjector
  abstract SecondFragment contributesActivity();
}
