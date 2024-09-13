package lexi

import kotlinx.datetime.Clock
import okio.FileSystem
import okio.Path.Companion.toPath

fun Map<String, String>.toFileAppenderOptions(
    system: FileSystem,
    clock: Clock,
): FileAppenderOptions = FileAppenderOptions(
    system = system,
    directory = get("directory")?.toPath() ?: throw IllegalArgumentException("Missing property 'directory' in file appender configration"),
    level = LogLevel.parse(getOrElse("level") { "DEBUG" }) ?: LogLevel.INFO,
    formatter = when (val formatter = getOrElse("formatter") { "simple" }) {
        "simple" -> SimpleLogFormatter(toSimpleFormatterOptions())
        "json" -> JsonLogFormatter(toJsonFormatterOptions())
        else -> throw IllegalArgumentException("Unknown formatter type: $formatter")
    },
    clock = clock
)

fun Map<String, String>.toFileAppender(system: FileSystem, clock: Clock) = FileAppender(toFileAppenderOptions(system, clock))