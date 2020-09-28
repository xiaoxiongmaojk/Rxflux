package cn.co.demo.rxflux.action;

import androidx.annotation.Nullable;

public abstract class Action<T> {
  private final T data;

  public T getData() {
    return data;
  }

  @Override
  public String toString() {
    return getType() + ":" + getData();
  }

  @Override public boolean equals(@Nullable final Object object) {
    if (object instanceof Action) {
      final Action target = (Action) object;
      if (this.getData() != null) {
        return this.getType().equals(target.getType()) && this.getData().equals(target.getData());
      }
      if (target.getData() != null) {
        return this.getType().equals(target.getType()) && target.getData().equals(this.getData());
      }
      return this.getType().equals(target.getType()) && this.getData() == target.getData();
    }
    return super.equals(object);
  }

  public abstract String getType();

  public Action(T data) {
    this.data = data;
  }
}
