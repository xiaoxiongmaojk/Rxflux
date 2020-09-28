package cn.co.demo.rxflux.di.applicaton;

import cn.co.demo.rxflux.view.SecondFragment;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@Subcomponent
public interface SecondFragmentComponent extends AndroidInjector<SecondFragment> {
  @Subcomponent.Builder
  abstract class Builder extends AndroidInjector.Builder<SecondFragment> {
  }
}
