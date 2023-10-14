package lexi

import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import okio.FileSystem
import okio.Path

@Serializable
class LoggingConfiguration(
    val level: LevelConfiguration? = null,
    var verbose: Boolean? = null,
    val appenders: List<AppenderConfiguration> = emptyList()
) {
    fun toOptions(
        system: FileSystem,
        clock: Clock,
        prefix: Path
    ) = appenders.map {
        val l = (it.level ?: level)?.toLevel() ?: LogLevel.DEBUG
        val v = it.verbose ?: verbose ?: true
        when (it) {
            is ConsoleAppenderConfiguration -> ConsoleAppenderOptions(
                level = l,
                verbose = v
            )

            is FileAppenderConfiguration -> FileAppenderOptions(
                system = system,
                directory = prefix / it.directory,
                clock = clock,
                level = l,
                verbose = v
            )
        }
    }

    fun toLogger(
        system: FileSystem,
        clock: Clock,
        prefix: Path
    ): Logger {
        val a = toOptions(system, clock, prefix).map {
            when(it) {
              is ConsoleAppenderOptions -> ConsoleAppender(it)
              is FileAppenderOptions -> FileAppender(it)
              else -> throw IllegalArgumentException("Unknown AppenderConfiguration ${it::class.simpleName}")
            }
        }
        return Logger(*a.toTypedArray())
    }
}