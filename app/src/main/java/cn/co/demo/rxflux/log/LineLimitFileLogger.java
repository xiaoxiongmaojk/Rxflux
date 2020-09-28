package cn.co.demo.rxflux.log;

import android.content.Context;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LineLimitFileLogger extends Log.AbstractLogger {
  private static final int LINE_LIMIT = 100000;
  protected final File mDirectory;
  protected final SimpleDateFormat mFileNameFormat;
  protected final int mLineLimit;
  protected int mLineCount;
  protected FileLogger mFileLogger;

  @Override protected void v(String tag, String message) {
    rollIfLimitOver();
    mFileLogger.v(tag, message);
  }

  @Override protected void v(String tag, String message, Throwable throwable) {
    rollIfLimitOver();
    mFileLogger.v(tag, message, throwable);
  }

  @Override protected void d(String tag, String message) {
    rollIfLimitOver();
    mFileLogger.d(tag, message);
  }

  @Override protected void d(String tag, String message, Throwable throwable) {
    rollIfLimitOver();
    mFileLogger.d(tag, message, throwable);
  }

  @Override protected void i(String tag, String message) {
    rollIfLimitOver();
    mFileLogger.i(tag, message);
  }

  @Override protected void i(String tag, String message, Throwable throwable) {
    rollIfLimitOver();
    mFileLogger.i(tag, message, throwable);
  }

  @Override protected void w(String tag, String message) {
    rollIfLimitOver();
    mFileLogger.w(tag, message);
  }

  @Override protected void w(String tag, String message, Throwable throwable) {
    rollIfLimitOver();
    mFileLogger.w(tag, message, throwable);
  }

  @Override protected void e(String tag, String message) {
    rollIfLimitOver();
    mFileLogger.e(tag, message);
  }

  @Override protected void e(String tag, String message, Throwable throwable) {
    rollIfLimitOver();
    mFileLogger.e(tag, message, throwable);
  }

  public LineLimitFileLogger(final File directory, final SimpleDateFormat fileNameFormat,
      final int lineLimit) {
    mDirectory = directory;
    mFileNameFormat = fileNameFormat;
    mLineLimit = lineLimit;
    mLineCount = 0;
    mFileLogger = new FileLogger(directory, fileNameFormat.format(new Date()));
  }

  protected synchronized void rollIfLimitOver() {
    if (++mLineCount > mLineLimit) {
      mLineCount = 0;
      mFileLogger.close();
      mFileLogger = new FileLogger(mDirectory, mFileNameFormat.format(new Date()));
    }
  }

  public static LineLimitFileLogger createDefaultLineLimitFileLogger(final Context context) {
    return new LineLimitFileLogger(new File(context.getFilesDir(), "logcat"),
        FileLogger.getFileNameFormat(), LINE_LIMIT);
  }
}
