package cn.co.demo.rxflux.log;

import android.content.Context;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AwsLimitFileLogger extends LineLimitFileLogger {
  private static final int LINE_LIMIT = 100000;
  private static final int FILESIZE_LIMIT = 700 * 1024;    // 700KB
  private final long mFileSizeLimit;

  public AwsLimitFileLogger(final File directory, final SimpleDateFormat fileNameFormat,
      final int lineLimit, final long fileSizeLimit) {
    super(directory, fileNameFormat, lineLimit);
    mFileSizeLimit = fileSizeLimit;
  }

  @Override
  protected synchronized void rollIfLimitOver() {
    if ((++mLineCount > mLineLimit) || (mFileLogger.getFileSize() >= mFileSizeLimit)) {
      mLineCount = 0;
      mFileLogger.close();
      mFileLogger = new FileLogger(mDirectory, mFileNameFormat.format(new Date()));
    }
  }

  public static AwsLimitFileLogger createDefaultLineLimitFileLogger(final Context context) {
    return new AwsLimitFileLogger(new File(context.getFilesDir(), "logcat"),
        FileLogger.getFileNameFormat(), LINE_LIMIT, FILESIZE_LIMIT);
  }

  /**
   * ファイル名を更新する
   */
  public void updateFileName() {
    mFileLogger.setFileName(mFileNameFormat.format(new Date()));
  }
}
