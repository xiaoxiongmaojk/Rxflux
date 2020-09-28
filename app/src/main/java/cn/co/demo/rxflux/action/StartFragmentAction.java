package cn.co.demo.rxflux.action;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.ref.WeakReference;

import cn.co.demo.rxflux.view.AbstractFragment;

public class StartFragmentAction {

  public static class StartFragment extends Action<StartFragment.StartFragmentParameters> {
    public static class StartFragmentParameters {
      public final String fragmentName;

      public StartFragmentParameters(@NonNull final String fragmentName) {
        this.fragmentName = fragmentName;
      }
    }

    public static final String TYPE = "StartFragmentAction.StartFragment";

    @Override public String getType() {
      return TYPE;
    }

    public StartFragment(final StartFragment.StartFragmentParameters data) {
      super(data);
    }
  }
  public static class FragmentInformation {
    public final Class<? extends Fragment> classObject;
    public final WeakReference<AbstractFragment> fragmentWeakReference;

    public FragmentInformation(
            final Class<? extends Fragment> classObject,
            final WeakReference<AbstractFragment> fragmentWeakReference) {
      this.classObject = classObject;
      this.fragmentWeakReference = fragmentWeakReference;
    }

    @Override public boolean equals(@Nullable final Object object) {
      if (object instanceof FragmentInformation) {
        final FragmentInformation target = (FragmentInformation) object;
        final AbstractFragment thisReference = this.fragmentWeakReference.get();
        final AbstractFragment targetReference = target.fragmentWeakReference.get();
        if (thisReference != null) {
          return this.classObject == target.classObject && thisReference.equals(targetReference);
        }
        return this.classObject == target.classObject && thisReference == targetReference;
      }
      return super.equals(object);
    }
  }

  public static class OnAttachFragment extends Action<FragmentInformation> {
    public static final String TYPE = "StartFragmentAction.OnAttachFragment";

    @Override public String getType() {
      return TYPE;
    }

    public OnAttachFragment(final FragmentInformation data) {
      super(data);
    }
  }
  public static class OnResumeFragment extends Action<FragmentInformation> {
    public static final String TYPE = "StartFragmentAction.OnResumeFragment";

    @Override public String getType() {
      return TYPE;
    }

    public OnResumeFragment(final FragmentInformation data) {
      super(data);
    }
  }

  public static class OnPauseFragment extends Action<FragmentInformation> {
    public static final String TYPE = "StartFragmentAction.OnPauseFragment";

    @Override public String getType() {
      return TYPE;
    }

    public OnPauseFragment(final FragmentInformation data) {
      super(data);
    }
  }

  public static class OnDestroyViewFragment extends Action<FragmentInformation> {
    public static final String TYPE = "StartFragmentAction.OnDestroyViewFragment";

    @Override public String getType() {
      return TYPE;
    }

    public OnDestroyViewFragment(final FragmentInformation data) {
      super(data);
    }
  }

  public static class OnDetachFragment extends Action<FragmentInformation> {
    public static final String TYPE = "StartFragmentAction.OnDetachFragment";

    @Override public String getType() {
      return TYPE;
    }

    public OnDetachFragment(final FragmentInformation data) {
      super(data);
    }
  }
}
