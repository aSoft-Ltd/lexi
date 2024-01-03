package lexi.builder

import lexi.ConsoleAppender
import lexi.ConsoleAppenderOptions
import lexi.FileAppender
import lexi.JsonLogFormatter
import lexi.JsonLogFormatterOptions
import lexi.LogLevel
import lexi.LoggerFactory
import lexi.SimpleLogFormatter
import lexi.SimpleLogFormatterOptions
import lexi.constants.LOG_FORMATTER_JSON_TYPE
import lexi.constants.LOG_FORMATTER_SIMPLE_TYPE

fun LoggingConfigurationBuilder.build(): LoggerFactory {
    val logger = LoggerFactory()
    logger.add(console.build(level, format))
    logger.add(file.build(level, format))
    return logger
}

private fun ConsoleAppenderConfigurationBuilder.build(
    level: String,
    formatter: LoggingFormatterConfigurationBuilder
) = takeIf { enabled == true }?.let {
    val opts = ConsoleAppenderOptions(
        level = LogLevel.parse(it.level ?: level) ?: LogLevel.DEBUG,
        formatter = it.format.build(formatter),
        verbose = formatter.verbose ?: true
    )
    ConsoleAppender(opts)
}

private fun FileAppenderConfigurationBuilder.build(
    level: String,
    formatter: LoggingFormatterConfigurationBuilder
): FileAppender? = takeIf { enabled == true }?.let {
    TODO()
//    val opts = FileAppenderOptions(
//        level = LogLevel.parse(it.level ?: level) ?: LogLevel.DEBUG,
//        formatter = it.format.build(formatter),
//    )
//    FileAppender(opts)
}

private fun LoggingFormatterConfigurationBuilder.build(default: LoggingFormatterConfigurationBuilder) = when (type) {
    LOG_FORMATTER_SIMPLE_TYPE -> {
        val options = SimpleLogFormatterOptions(
            separator = separator ?: default.separator ?: "=",
            verbose = verbose ?: default.verbose ?: true,
            status = status ?: default.status ?: true,
            source = source ?: default.status ?: true
        )
        SimpleLogFormatter(options)
    }

    LOG_FORMATTER_JSON_TYPE -> {
        val options = JsonLogFormatterOptions(
            tab = tab ?: default.tab ?: "  ",
            verbose = verbose ?: default.verbose ?: true,
            status = status ?: default.status ?: true,
            source = source ?: default.status ?: true
        )
        JsonLogFormatter(options)
    }

    else -> throw IllegalArgumentException("Unknown formatter type $type")
}