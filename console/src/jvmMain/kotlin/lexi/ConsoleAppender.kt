package lexi

actual class ConsoleAppender actual constructor(var options: ConsoleAppenderOptions) : Appender {
    override fun append(level: LogLevel, msg: String, vararg data: Pair<String, Any?>) {
        if (level >= options.level) {
            val stream = if (level >= LogLevel.ERROR) System.err else System.out
            val log = Log(level, msg, null, data.toMap())
            stream.println(options.formatter.format(log))
        }
    }

    override fun append(vararg o: Any?) = o.forEach { println(it) }
}