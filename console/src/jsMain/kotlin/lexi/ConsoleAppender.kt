package lexi

import lexi.external.console
import lexi.internal.AbstractAppender

actual class ConsoleAppender actual constructor(
    var options: ConsoleAppenderOptions
) : AbstractAppender(), Appender {

    override fun append(log: Log) {
        val level = log.level
        if (level >= options.level) {
            val printer: (Any?) -> Unit = when (level) {
                LogLevel.DEBUG -> console::debug
                LogLevel.TRACE -> console::trace
                LogLevel.INFO -> console::info
                LogLevel.WARNING -> console::warn
                LogLevel.ERROR -> console::error
                LogLevel.FATAL -> console::error
            }
            val formatted = options.formatter.format(log)
            if (options.formatter is JsonLogFormatter) {
                try {
                    printer(JSON.parse<dynamic>(formatted))
                } catch (_: Throwable) {
                    printer(formatted)
                }
            } else {
                printer(formatted)
            }
        }
    }
}