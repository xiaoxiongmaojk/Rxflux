package cn.co.demo.rxflux.view;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.os.Bundle;

import javax.inject.Inject;
import cn.co.demo.rxflux.R;
import cn.co.demo.rxflux.ViewDataBinder;
import cn.co.demo.rxflux.action.Action;
import cn.co.demo.rxflux.action.StartFragmentAction;
import cn.co.demo.rxflux.actionCreator.StartFragmentActionCreator;
import cn.co.demo.rxflux.databinding.ActivityMainBinding;
import cn.co.demo.rxflux.log.Log;
import cn.co.demo.rxflux.store.SampleStore;

public class RxfluxMainActivity extends AbstractActivity {

  @Inject
  StartFragmentActionCreator mStartFragmentActionCreator;
  @Inject
  SampleStore mSampleStore;
  private static final String TAG = RxfluxMainActivity.class.getSimpleName();
  private ActivityMainBinding activityMainBinding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    ViewDataBinder.setViewDataBindings(activityMainBinding, this);
    activityMainBinding.setLifecycleOwner(this);
    activityMainBinding.setMessageContainerVisible(true);
    mStartFragmentActionCreator.startFragment(null);

  }

  @Override
  protected void doStartFragment(Action action) {

    mSampleStore.setText("success");
    Log.d(TAG, "doStartFragment enter");
    if (hasSavedInstanceState() || isFinishing() || isDestroyed()) {
      Log.d(TAG, "doStartFragment exit, Activity is inactive");
      return;
    }
    final StartFragmentAction.StartFragment.StartFragmentParameters startFragmentParameters =
            (StartFragmentAction.StartFragment.StartFragmentParameters) action.getData();
    try {
      final Fragment fragment = (Fragment) Class.forName(startFragmentParameters.fragmentName).newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_container, fragment).addToBackStack(null).commit();
      activityMainBinding.setMessageContainerVisible(false);
    } catch (ReflectiveOperationException e) {
      Log.w(TAG, "doStartFragment", e);
    }
  }
}
