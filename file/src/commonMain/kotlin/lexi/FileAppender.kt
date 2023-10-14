package lexi

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import okio.buffer

class FileAppender(private val options: FileAppenderOptions) : Appender {
    private val system = options.system

    init {
        if (!system.exists(options.directory)) {
            system.createDirectories(options.directory)
        }
    }

    private val now get() = options.clock.now().toLocalDateTime(TimeZone.UTC)

    //    private val dir get() = File(options.directory, now.toDirFormat()).also { if (!it.exists()) it.mkdirs() }
    private val dir
        get() = (options.directory / now.toDirFormat()).also {
            if (!system.exists(it)) system.createDirectories(it)
        }

    private val file get() = dir / (now.hour.to2digits() + ".log")

    private fun Int.to2digits() = if (this <= 9) "0$this" else "$this"

    private fun LocalDateTime.toDirFormat() = "$year/${monthNumber.to2digits()}/${dayOfMonth.to2digits()}"

    override fun append(level: LogLevel, msg: String, vararg data: Pair<String, Any?>) {
        if (level > options.level) system.write(file) {
            writeUtf8("---->\n")
            writeUtf8("${level.name}: $msg\n")
            data.forEach {
                writeUtf8("${it.first}: ${it.second.toString()}\n")
            }
        }
    }

    override fun append(vararg o: Any?) = o.forEach { append(LogLevel.DEBUG, msg = it.toString()) }
}