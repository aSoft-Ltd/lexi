package lexi

import lexi.internal.AbstractAppender
import android.util.Log as ALog

actual class ConsoleAppender actual constructor(var options: ConsoleAppenderOptions) : AbstractAppender(), Appender {

    actual override fun append(log: Log) {
        val level = log.level
        if (level >= options.level) {
            val printer: (String, String) -> Unit = when (level) {
                LogLevel.DEBUG -> { tag, txt -> ALog.d(tag, txt) }
                LogLevel.TRACE -> { tag, txt -> ALog.v(tag, txt) }
                LogLevel.INFO -> { tag, txt -> ALog.i(tag, txt) }
                LogLevel.WARNING -> { tag, txt -> ALog.w(tag, txt) }
                LogLevel.ERROR -> { tag, txt -> ALog.e(tag, txt) }
                LogLevel.FATAL -> { tag, txt -> ALog.wtf(tag, txt) }
            }
            printer(log.source, options.formatter.format(log))
        }
    }
}