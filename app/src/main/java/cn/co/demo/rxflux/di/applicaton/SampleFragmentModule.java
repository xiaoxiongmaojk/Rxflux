package cn.co.demo.rxflux.di.applicaton;

import cn.co.demo.rxflux.view.SampleFragment;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SampleFragmentModule {

  @ContributesAndroidInjector
  abstract SampleFragment contributesActivity();
}
