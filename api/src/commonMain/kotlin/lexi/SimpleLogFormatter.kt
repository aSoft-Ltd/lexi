package lexi

class SimpleLogFormatter(private val options: SimpleLogFormatterOptions = SimpleLogFormatterOptions()) : LogFormatter {
    override fun format(log: Log) = buildString {
        if (options.separator != null) {
            appendLine("${options.separator} ".repeat(13) + "S T A R T" + " ${options.separator}".repeat(13))
        }
        appendLine("""[${log.level.name}]: ${log.message}""")
        if (log.status != null && options.status) {
            appendLine("Status: ${log.status.name}")
        }
        if (options.source) {
            appendLine("Source: ${log.source}")
        }
        if (options.verbose) log.metadata.entries.forEach { (key, value) ->
            if (key != "source") appendLine("""$key: $value""")
        }

        if (options.separator != null) {
            appendLine("${options.separator} ".repeat(14) + "E N D" + " ${options.separator}".repeat(14))
        }
    }
}