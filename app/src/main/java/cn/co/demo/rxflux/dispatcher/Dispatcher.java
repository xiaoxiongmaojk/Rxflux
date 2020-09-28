package cn.co.demo.rxflux.dispatcher;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import cn.co.demo.rxflux.action.Action;
import cn.co.demo.rxflux.di.PerApplicationScope;
import cn.co.demo.rxflux.log.Log;

@PerApplicationScope
public class Dispatcher {
  private final FlowableProcessor<Action> mFlowableProcessor = PublishProcessor.create();

  @Inject
  public Dispatcher() {
    Log.v(this.getClass().getSimpleName(),
        this.getClass().getSimpleName() + "():" + Integer.toHexString(hashCode()));
  }

  @Override protected void finalize() throws Throwable {
    super.finalize();
    Log.v(this.getClass().getSimpleName(), "finalize:" + Integer.toHexString(hashCode()));
  }

  public <T> void dispatch(final Action<T> action) {
    mFlowableProcessor.onNext(action);
  }

  public Flowable<Action> on(final String type) {
    return mFlowableProcessor.filter(action -> action.getType().equals(type));
  }
}
