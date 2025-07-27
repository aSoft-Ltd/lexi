@file:OptIn(ExperimentalTime::class)

package lexi

import kotlin.time.Clock
import okio.FileSystem
import okio.Path
import kotlin.time.ExperimentalTime

class FileAppenderOptions(
    val system: FileSystem,
    val directory: Path,
    val level: LogLevel = LogLevel.DEBUG,
    val formatter: LogFormatter,
    val clock: Clock
)