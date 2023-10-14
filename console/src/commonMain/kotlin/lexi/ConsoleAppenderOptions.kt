package lexi

class ConsoleAppenderOptions(
    val level: LogLevel = LogLevel.DEBUG,
    val formatter: LogFormatter,// = SimpleLogFormatter(verbose = true),
    val verbose: Boolean = true
)