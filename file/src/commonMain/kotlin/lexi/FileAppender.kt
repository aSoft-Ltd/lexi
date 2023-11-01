package lexi

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import lexi.internal.AbstractAppender

class FileAppender(private val options: FileAppenderOptions) : AbstractAppender(), Appender {
    private val system = options.system

    init {
        if (!system.exists(options.directory)) {
            system.createDirectories(options.directory)
        }
    }

    private val now get() = options.clock.now().toLocalDateTime(TimeZone.UTC)

    private val dir
        get() = (options.directory / now.toDirFormat()).also {
            if (!system.exists(it)) system.createDirectories(it)
        }

    private val file get() = dir / (now.hour.to2digits() + ".log")

    private fun Int.to2digits() = if (this <= 9) "0$this" else "$this"

    private fun LocalDateTime.toDirFormat() = "$year/${monthNumber.to2digits()}/${dayOfMonth.to2digits()}"

    override fun append(log: Log) {
        if (log.level > options.level) system.write(file) {
            writeUtf8(options.formatter.format(log))
        }
    }
}