package cn.co.demo.rxflux.log;

import android.util.Log;

public class LogcatLogger extends cn.co.demo.rxflux.log.Log.AbstractLogger {
  @Override protected void v(final String tag, final String message) {
    Log.v(tag, message);
  }

  @Override protected void v(final String tag, final String message, final Throwable throwable) {
    Log.v(tag, message, throwable);
  }

  @Override protected void d(final String tag, final String message) {
    Log.d(tag, message);
  }

  @Override protected void d(final String tag, final String message, final Throwable throwable) {
    Log.d(tag, message, throwable);
  }

  @Override protected void i(final String tag, final String message) {
    Log.i(tag, message);
  }

  @Override protected void i(final String tag, final String message, final Throwable throwable) {
    Log.i(tag, message, throwable);
  }

  @Override protected void w(final String tag, final String message) {
    Log.w(tag, message);
  }

  @Override protected void w(final String tag, final String message, final Throwable throwable) {
    Log.w(tag, message, throwable);
  }

  @Override protected void e(final String tag, final String message) {
    Log.e(tag, message);
  }

  @Override protected void e(final String tag, final String message, final Throwable throwable) {
    Log.e(tag, message, throwable);
  }
}
