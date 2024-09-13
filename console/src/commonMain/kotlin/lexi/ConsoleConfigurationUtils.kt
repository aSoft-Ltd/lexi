package lexi

fun Map<String, String>.toConsoleAppenderOptions(): ConsoleAppenderOptions = ConsoleAppenderOptions(
    level = LogLevel.parse(getOrElse("level") { "DEBUG" }) ?: LogLevel.DEBUG,
    verbose = getOrElse("verbose") { "true" }.toBoolean(),
    formatter = when (val formatter = getOrElse("formatter") { "simple" }) {
        "simple" -> SimpleLogFormatter(toSimpleFormatterOptions())
        "json" -> JsonLogFormatter(toJsonFormatterOptions())
        else -> throw IllegalArgumentException("Unknown formatter type: $formatter")
    }
)

fun Map<String, String>.toConsoleAppender() = ConsoleAppender(toConsoleAppenderOptions())