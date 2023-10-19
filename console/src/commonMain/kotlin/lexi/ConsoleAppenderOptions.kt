package lexi

class ConsoleAppenderOptions(
    val level: LogLevel = LogLevel.DEBUG,
    val formatter: LogFormatter,
    val verbose: Boolean = true
)