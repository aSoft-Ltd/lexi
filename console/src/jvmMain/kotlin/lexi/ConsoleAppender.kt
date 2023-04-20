package lexi

actual class ConsoleAppender actual constructor(var options: ConsoleAppenderOptions) : Appender {
    override fun append(level: LogLevel, msg: String, vararg data: Pair<String, Any?>) {
        if (level >= options.level) {
            val stream = if (level >= LogLevel.ERROR) System.err else System.out
            if (options.verbose) {
                stream.println("\n" + "= ".repeat(31))
                stream.println("${level.name}: $msg")
                data.forEach {
                    stream.println("${it.first}: ${it.second}")
                }
                stream.println("= ".repeat(31))
            } else {
                stream.println("${level.name}: $msg")
            }
        }
    }

    override fun append(vararg o: Any?) = o.forEach { println(it) }
}