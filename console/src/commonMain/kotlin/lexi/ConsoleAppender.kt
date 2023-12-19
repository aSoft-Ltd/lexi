package lexi

import lexi.internal.AbstractAppender

expect class ConsoleAppender(options: ConsoleAppenderOptions) : AbstractAppender, Appender {
    override fun append(log: Log)
}