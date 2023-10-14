package lexi

fun ConsoleAppender(level: LogLevel = LogLevel.DEBUG, formatter: LogFormatter = SimpleLogFormatter()) = ConsoleAppender(
    ConsoleAppenderOptions(level, formatter)
)