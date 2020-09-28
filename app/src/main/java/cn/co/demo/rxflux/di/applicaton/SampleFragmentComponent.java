package cn.co.demo.rxflux.di.applicaton;

import cn.co.demo.rxflux.view.SampleFragment;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface SampleFragmentComponent extends AndroidInjector<SampleFragment> {
  @Subcomponent.Builder
  abstract class Builder extends AndroidInjector.Builder<SampleFragment> {
  }
}
