package logging

@Deprecated("use lexi instead")
class ConsoleAppenderOptions(
    val level: LogLevel = LogLevel.DEBUG,
    val verbose: Boolean = true
)