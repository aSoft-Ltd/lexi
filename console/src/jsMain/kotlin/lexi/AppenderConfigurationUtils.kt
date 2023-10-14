package lexi

import lexi.console.constants.CONSOLE_APPENDER_TYPE
import lexi.constants.LOG_FORMATTER_JSON_TYPE
import lexi.constants.LOG_FORMATTER_SIMPLE_TYPE

fun LoggingConfigurationJson.toConsoleAppenders() = appenders.mapNotNull {
    if (it.type != CONSOLE_APPENDER_TYPE) return@mapNotNull null
    ConsoleAppender(
        level = LogLevel.parse(it.level ?: level) ?: LogLevel.DEBUG,
        formatter = it.formatter.toFormatter(verbose, source, status)
    )
}

fun LogFormatterConfigurationJson?.toFormatter(
    defaultVerbose: Boolean?,
    defaultSource: Boolean?,
    defaultStatus: Boolean?
): LogFormatter {
    if (this == null) {
        return JsonLogFormatter()
    }

    @Suppress("SENSELESS_NULL_IN_WHEN")
    return when (type) {
        LOG_FORMATTER_JSON_TYPE -> JsonLogFormatter(
            JsonLogFormatterOptions(
                verbose = verbose ?: defaultVerbose ?: true,
                status = status ?: defaultStatus ?: true,
                source = source ?: defaultSource ?: true
            )
        )

        LOG_FORMATTER_SIMPLE_TYPE -> SimpleLogFormatter(
            SimpleLogFormatterOptions(
                verbose = verbose ?: defaultVerbose ?: true,
                status = status ?: defaultStatus ?: true,
                source = source ?: defaultSource ?: true
            )
        )

        null -> JsonLogFormatter()
        else -> throw IllegalArgumentException("Unknown formatter type: ${type}. Supported types are [simple,json]")
    }
}