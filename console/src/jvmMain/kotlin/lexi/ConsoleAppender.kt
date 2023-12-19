package lexi

import lexi.internal.AbstractAppender

actual class ConsoleAppender actual constructor(var options: ConsoleAppenderOptions) : AbstractAppender(), Appender {
    actual override fun append(log: Log) {
        val level = log.level
        if (level >= options.level) {
            val stream = if (level >= LogLevel.WARNING) System.err else System.out
            stream.println(options.formatter.format(log))
        }
    }
}