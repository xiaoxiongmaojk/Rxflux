package cn.co.demo.rxflux.log;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;

public class FileLogger extends Log.AbstractLogger {
  private static final String[] LOG_PRIORITIES =
      new String[] { "/", "/", "V/", "D/", "I/", "W/", "E/", "A/" };
  private static final String LINE_SEPARATOR = System.lineSeparator();
  private static volatile SimpleDateFormat sTimeStampFormat = null;

  private static SimpleDateFormat getTimeStampFormat() {
    SimpleDateFormat simpleDateFormat = sTimeStampFormat;
    if (simpleDateFormat == null) {
      sTimeStampFormat = simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
    }
    return simpleDateFormat;
  }

  private static volatile SimpleDateFormat sFileNameFormat = null;

  public static SimpleDateFormat getFileNameFormat() {
    SimpleDateFormat simpleDateFormat = sFileNameFormat;
    if (simpleDateFormat == null) {
      sFileNameFormat = simpleDateFormat =
          new SimpleDateFormat("'APLG_'yyyyMMddHHmmssSSS'.json'");
    }
    return simpleDateFormat;
  }

  private static final class LogElement {
    final long timeStamp;
    final int priority;
    final String tag;
    final String message;
    final Throwable throwable;

    LogElement(final long timeStamp, final int priority, final String tag,
        final String message) {
      this(timeStamp, priority, tag, message, null);
    }

    LogElement(final long timeStamp, final int priority, final String tag, final String message,
        final Throwable throwable) {
      this.timeStamp = timeStamp;
      this.priority = priority;
      this.tag = tag;
      this.message = message;
      this.throwable = throwable;
    }

    @Override public String toString() {
      if (throwable != null) {
        final StringBuilder stringBuilder =
            new StringBuilder(getTimeStampFormat().format(new Date(timeStamp)))
                .append(" ")
                .append(LOG_PRIORITIES[priority])
                .append(tag)
                .append(": ")
                .append(message)
                .append(LINE_SEPARATOR);
        appendThrowable(stringBuilder, "", throwable);
        return stringBuilder.toString();
      } else {
        return getTimeStampFormat().format(new Date(timeStamp))
            + " "
            + LOG_PRIORITIES[priority]
            + tag
            + ": "
            + message
            + LINE_SEPARATOR;
      }
    }

    private StringBuilder appendThrowable(final StringBuilder stringBuilder,
        final String prefix, final Throwable throwable) {
      if (stringBuilder == null || throwable == null) return stringBuilder;
      stringBuilder.append("\t")
          .append(prefix)
          .append(throwable.toString())
          .append(LINE_SEPARATOR);
      for (final StackTraceElement each : throwable.getStackTrace()) {
        stringBuilder.append("\t\t")
            .append("at ")
            .append(each.getClassName())
            .append("(")
            .append(each.getFileName())
            .append(":")
            .append(each.getLineNumber())
            .append(")")
            .append(LINE_SEPARATOR);
      }
      if (throwable.getCause() != null) {
        appendThrowable(stringBuilder, " Caused by: ", throwable.getCause());
      }
      for (final Throwable each : throwable.getSuppressed()) {
        appendThrowable(stringBuilder, "", each);
      }
      return stringBuilder;
    }
  }

  private final class FileOutputThread extends Thread {
    private boolean mIsRunning = true;

    FileOutputThread(final String name) {
      super(name);
    }

    @Override public void run() {
      mDirectory.mkdirs();
      try (final FileWriter fileWriter = new FileWriter(mFile, true)) {
        LogElement logElement = null;
        while (mIsRunning || logElement != null) {
          try {
            logElement = mBuffer.take();
          } catch (InterruptedException e) {
            // ignore.
          }
          if (logElement == null) continue;
          if (!mFile.exists()) {
            try {
              mFile = new File(mDirectory, mFileName);
              final FileWriter tempFileWriter = new FileWriter(mFile, true);
              tempFileWriter.write(logElement.toString());
              tempFileWriter.close();
            } catch (final IOException e0) {
              // ignore.
            }
            run();
            return;
          }
          fileWriter.write(logElement.toString());
          fileWriter.flush();
        }
      } catch (IOException e1) {
        // TODO: ignore now
      }
    }

    void close() {
      mIsRunning = false;
    }
  }

  private final File mDirectory;
  private String mFileName;
  private File mFile;
  private final FileOutputThread mFileOutputThread;
  private final LinkedBlockingDeque<LogElement> mBuffer = new LinkedBlockingDeque<>();

  @Override protected void v(final String tag, final String message) {
    offer(android.util.Log.VERBOSE, tag, message, null);
  }

  @Override protected void v(final String tag, final String message, final Throwable throwable) {
    offer(android.util.Log.VERBOSE, tag, message, throwable);
  }

  @Override protected void d(final String tag, final String message) {
    offer(android.util.Log.DEBUG, tag, message, null);
  }

  @Override protected void d(final String tag, final String message, final Throwable throwable) {
    offer(android.util.Log.DEBUG, tag, message, throwable);
  }

  @Override protected void i(final String tag, final String message) {
    offer(android.util.Log.INFO, tag, message, null);
  }

  @Override protected void i(final String tag, final String message, final Throwable throwable) {
    offer(android.util.Log.INFO, tag, message, throwable);
  }

  @Override protected void w(final String tag, final String message) {
    offer(android.util.Log.WARN, tag, message, null);
  }

  @Override protected void w(final String tag, final String message, final Throwable throwable) {
    offer(android.util.Log.WARN, tag, message, throwable);
  }

  @Override protected void e(final String tag, final String message) {
    offer(android.util.Log.ERROR, tag, message, null);
  }

  @Override protected void e(final String tag, final String message, final Throwable throwable) {
    offer(android.util.Log.ERROR, tag, message, throwable);
  }

  public FileLogger(final File directory, final String fileName) {
    mDirectory = directory;
    mFileName = fileName;
    mFile = new File(mDirectory, mFileName);
    mFileOutputThread = new FileOutputThread(getClass().getSimpleName());
    mFileOutputThread.start();
  }

  private void offer(final int priority, final String tag, final String message,
      final Throwable throwable) {
    mBuffer.offer(new LogElement(System.currentTimeMillis(), priority, tag, message, throwable));
  }

  public void close() {
    mFileOutputThread.close();
  }

  public long getFileSize() {
    return (mFile != null) ? mFile.length() : 0L;
  }

  public void setFileName(@NonNull final String fileName) {
    mFileName = fileName;
  }
}
