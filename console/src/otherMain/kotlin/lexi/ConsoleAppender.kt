package lexi

import lexi.internal.AbstractAppender

actual class ConsoleAppender actual constructor(var options: ConsoleAppenderOptions) : AbstractAppender(), Appender {

    override fun append(log: Log) {
        val level = log.level
        if (level >= options.level) {
            println(options.formatter.format(log))
        }
    }
}