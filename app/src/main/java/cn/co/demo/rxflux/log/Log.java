package cn.co.demo.rxflux.log;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

public final class Log {
  public static final int LOG_LEVEL_VERBOSE = 0;
  public static final int LOG_LEVEL_DEBUG = 1;
  public static final int LOG_LEVEL_INFO = 2;
  public static final int LOG_LEVEL_WARN = 3;
  public static final int LOG_LEVEL_ERROR = 4;
  public static final int LOG_LEVEL_ASSERT = 5;

  @IntDef({
      LOG_LEVEL_VERBOSE,
      LOG_LEVEL_DEBUG,
      LOG_LEVEL_INFO,
      LOG_LEVEL_WARN,
      LOG_LEVEL_ERROR,
      LOG_LEVEL_ASSERT
  })
  @Retention(RetentionPolicy.SOURCE)
  public @interface LogLevel {
  }
  public static @LogLevel int sLogLevel = LOG_LEVEL_VERBOSE;

  private static final List<AbstractLogger> ABSTRACT_LOGGER_LIST = new ArrayList<>();
  private static volatile AbstractLogger[] sLoggersArray = new AbstractLogger[0];
  private static final AbstractLogger ABSTRACT_LOGGERS = new AbstractLogger() {
    @Override protected void v(String tag, String message) {
      if (sLogLevel > LOG_LEVEL_VERBOSE) {
        return;
      }
      for (final AbstractLogger each : sLoggersArray) {
        each.v(tag, message);
      }
    }

    @Override protected void v(String tag, String message, Throwable throwable) {
      if (sLogLevel > LOG_LEVEL_VERBOSE) {
        return;
      }
      for (final AbstractLogger each : sLoggersArray) {
        each.v(tag, message, throwable);
      }
    }

    @Override protected void d(String tag, String message) {
      if (sLogLevel > LOG_LEVEL_DEBUG) {
        return;
      }
      for (final AbstractLogger each : sLoggersArray) {
        each.d(tag, message);
      }
    }

    @Override protected void d(String tag, String message, Throwable throwable) {
      if (sLogLevel > LOG_LEVEL_DEBUG) {
        return;
      }
      for (final AbstractLogger each : sLoggersArray) {
        each.d(tag, message, throwable);
      }
    }

    @Override protected void i(String tag, String message) {
      if (sLogLevel > LOG_LEVEL_INFO) {
        return;
      }
      for (final AbstractLogger each : sLoggersArray) {
        each.i(tag, message);
      }
    }

    @Override protected void i(String tag, String message, Throwable throwable) {
      if (sLogLevel > LOG_LEVEL_INFO) {
        return;
      }
      for (final AbstractLogger each : sLoggersArray) {
        each.i(tag, message, throwable);
      }
    }

    @Override protected void w(String tag, String message) {
      if (sLogLevel > LOG_LEVEL_WARN) {
        return;
      }
      for (final AbstractLogger each : sLoggersArray) {
        each.w(tag, message);
      }
    }

    @Override protected void w(String tag, String message, Throwable throwable) {
      if (sLogLevel > LOG_LEVEL_WARN) {
        return;
      }
      for (final AbstractLogger each : sLoggersArray) {
        each.w(tag, message, throwable);
      }
    }

    @Override protected void e(final String tag, final String message) {
      if (sLogLevel > LOG_LEVEL_ERROR) {
        return;
      }
      for (final AbstractLogger each : sLoggersArray) {
        each.e(tag, message);
      }
    }

    @Override protected void e(String tag, String message, Throwable throwable) {
      if (sLogLevel > LOG_LEVEL_ERROR) {
        return;
      }
      for (final AbstractLogger each : sLoggersArray) {
        each.e(tag, message, throwable);
      }
    }
  };

  private Log() {
    throw new AssertionError();
  }

  public static void addLogger(final AbstractLogger logger) {
    if (logger == null) return;
    synchronized (ABSTRACT_LOGGER_LIST) {
      ABSTRACT_LOGGER_LIST.add(logger);
      sLoggersArray = ABSTRACT_LOGGER_LIST.toArray(new AbstractLogger[ABSTRACT_LOGGER_LIST.size()]);
    }
  }

  public static void removeLogger(final AbstractLogger logger) {
    if (logger == null) return;
    synchronized (ABSTRACT_LOGGER_LIST) {
      if (ABSTRACT_LOGGER_LIST.remove(logger)) {
        sLoggersArray =
            ABSTRACT_LOGGER_LIST.toArray(new AbstractLogger[ABSTRACT_LOGGER_LIST.size()]);
      }
    }
  }

  public static void v(final String tag, final String message) {
    ABSTRACT_LOGGERS.v(tag, message);
  }

  public static void v(final String tag, final String message, final Throwable throwable) {
    ABSTRACT_LOGGERS.v(tag, message, throwable);
  }

  public static void d(final String tag, final String message) {
    ABSTRACT_LOGGERS.d(tag, message);
  }

  public static void d(final String tag, final String message, final Throwable throwable) {
    ABSTRACT_LOGGERS.d(tag, message, throwable);
  }

  public static void i(final String tag, final String message) {
    ABSTRACT_LOGGERS.i(tag, message);
  }

  public static void i(final String tag, final String message, final Throwable throwable) {
    ABSTRACT_LOGGERS.i(tag, message, throwable);
  }

  public static void w(final String tag, final String message) {
    ABSTRACT_LOGGERS.w(tag, message);
  }

  public static void w(final String tag, final String message, final Throwable throwable) {
    ABSTRACT_LOGGERS.w(tag, message, throwable);
  }

  public static void e(final String tag, final String message) {
    ABSTRACT_LOGGERS.e(tag, message);
  }

  public static void e(final String tag, final String message, final Throwable throwable) {
    ABSTRACT_LOGGERS.e(tag, message, throwable);
  }

  public static abstract class AbstractLogger {
    protected abstract void v(final String tag, final String message);

    protected abstract void v(final String tag, final String message, final Throwable throwable);

    protected abstract void d(final String tag, final String message);

    protected abstract void d(final String tag, final String message, final Throwable throwable);

    protected abstract void i(final String tag, final String message);

    protected abstract void i(final String tag, final String message, final Throwable throwable);

    protected abstract void w(final String tag, final String message);

    protected abstract void w(final String tag, final String message, final Throwable throwable);

    protected abstract void e(final String tag, final String message);

    protected abstract void e(final String tag, final String message, final Throwable throwable);
  }
}
