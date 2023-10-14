package lexi

import kotlinx.datetime.Clock
import okio.FileSystem
import okio.Path

class FileAppenderOptions(
    val system: FileSystem,
    val directory: Path,
    val level: LogLevel = LogLevel.DEBUG,
    val verbose: Boolean = true,
    val clock: Clock
)