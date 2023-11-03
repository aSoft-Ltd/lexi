package lexi

import kotlinx.datetime.Clock
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import lexi.appender.AppenderConfiguration
import lexi.appender.ConsoleAppenderConfiguration
import lexi.appender.FileAppenderConfiguration
import lexi.formatter.JsonFormatterConfiguration
import lexi.formatter.SimpleLogFormatterConfiguration
import lexi.formatter.toFormatter
import okio.FileSystem
import okio.Path

@Serializable
class LoggingConfiguration(
    val level: String? = null,
    val source: Boolean? = null,
    val status: Boolean? = null,
    val verbose: Boolean? = null,
    @SerialName("appender")
    val appenders: List<AppenderConfiguration> = emptyList()
) {
    fun toOptions(
        system: FileSystem,
        clock: Clock,
        prefix: Path
    ) = appenders.map {
        val l = LogLevel.parse(it.level ?: level) ?: LogLevel.DEBUG

        when (it) {
            is ConsoleAppenderConfiguration -> ConsoleAppenderOptions(
                level = l,
                formatter = when (val formatter = it.format) {
                    is SimpleLogFormatterConfiguration -> formatter.toFormatter(verbose, source, status)
                    is JsonFormatterConfiguration -> formatter.toFormatter(verbose, source, status)
                    null -> SimpleLogFormatter()
                }
            )

            is FileAppenderConfiguration -> FileAppenderOptions(
                system = system,
                directory = prefix / it.directory,
                clock = clock,
                level = l,
                formatter = when (val formatter = it.format) {
                    is SimpleLogFormatterConfiguration -> formatter.toFormatter(verbose, source, status)
                    is JsonFormatterConfiguration -> formatter.toFormatter(verbose, source, status)
                    null -> SimpleLogFormatter(SimpleLogFormatterOptions())
                }
            )
        }
    }

    fun toLogger(
        system: FileSystem,
        clock: Clock,
        prefix: Path
    ): LoggerFactory {
        val appenders = toOptions(system, clock, prefix).map {
            when (it) {
                is ConsoleAppenderOptions -> ConsoleAppender(it)
                is FileAppenderOptions -> FileAppender(it)
                else -> throw IllegalArgumentException("Unknown AppenderConfiguration ${it::class.simpleName}")
            }
        }
        return LoggerFactory("Default Factory").apply { addAll(appenders) }
    }
}

