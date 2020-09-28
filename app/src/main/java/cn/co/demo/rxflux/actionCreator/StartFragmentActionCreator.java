package cn.co.demo.rxflux.actionCreator;

import android.view.View;
import javax.inject.Inject;
import cn.co.demo.rxflux.action.StartFragmentAction;
import cn.co.demo.rxflux.di.PerApplicationScope;
import cn.co.demo.rxflux.dispatcher.Dispatcher;
import cn.co.demo.rxflux.ViewDataBindee;
import cn.co.demo.rxflux.view.SampleFragment;
import cn.co.demo.rxflux.view.SecondFragment;

@PerApplicationScope
public class StartFragmentActionCreator implements ViewDataBindee {

  private final Dispatcher mDispatcher;

  @Inject
  public StartFragmentActionCreator(final Dispatcher dispatcher) {
    mDispatcher = dispatcher;
  }

  public void startFragment(View view) {
    mDispatcher.dispatch(new StartFragmentAction.StartFragment(
            new StartFragmentAction.StartFragment.StartFragmentParameters(SampleFragment.class.getName())));
  }

  public void startSecondFragment(View view) {
    mDispatcher.dispatch(new StartFragmentAction.StartFragment(
            new StartFragmentAction.StartFragment.StartFragmentParameters(SecondFragment.class.getName())));
  }
}
