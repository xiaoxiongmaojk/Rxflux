package cn.co.demo.rxflux;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import cn.co.demo.rxflux.log.Log;

public interface ViewDataBinder {
  static void setViewDataBindings(final ViewDataBinding bindingTo,
                                  final ViewDataBinder bindingFrom) {
    Class clazz = bindingFrom.getClass();
    while ((clazz != null) && (clazz != Object.class)) {
      for (final Field each : clazz.getDeclaredFields()) {
        each.setAccessible(true);
        try {
          if (each.get(bindingFrom) instanceof ViewDataBindee) {
            setViewDataBinding(bindingTo, (ViewDataBindee) each.get(bindingFrom));
          }
        } catch (IllegalAccessException e) {
          // ignore.
        }
      }
      clazz = clazz.getSuperclass();
    }
  }

  static void setViewDataBinding(@NonNull final ViewDataBinding bindingTo,
                                 final ViewDataBindee bindingFrom) {
    try {
      Class clazz = bindingTo.getClass().getSuperclass();
      while ((clazz != null) && (clazz != Object.class)) {
        for (final Field each : clazz.getDeclaredFields()) {
          each.setAccessible(true);
          if (each.get(bindingTo) instanceof ViewDataBinding) {
            setViewDataBinding((ViewDataBinding) each.get(bindingTo), bindingFrom);
          }
        }
        clazz = clazz.getClass().getSuperclass();
      }
      // 以下を条件に難読化された**BindingImpl.set**メソッドを特定し実行する
      // 1.[**BindingImpl]クラスのpublicメソッドであること
      // 2.メソッドの戻り値が[void]であること
      // 3.メソッドの引数が1つであること
      // 4.メソッドの引数が[* extends ViewDataBindee]型であること
      for (final Method each : bindingTo.getClass().getMethods()) {
        if ((each.getReturnType() == Void.TYPE)
            && (each.getParameterTypes().length == 1)
            && (each.getParameterTypes()[0] == bindingFrom.getClass())) {
          each.invoke(bindingTo, bindingFrom);
          return;
        }
      }
    } catch (ReflectiveOperationException e) {
      Log.d(ViewDataBinder.class.getSimpleName(), "setViewDataBinding", e);
    }
  }
}
