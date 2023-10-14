package lexi

class JsonLogFormatter(private val options: JsonLogFormatterOptions = JsonLogFormatterOptions()) : LogFormatter {
    override fun format(log: Log) = buildMap {
        put("level", log.level.name)
        put("message", log.message)
        if (options.source) {
            put("source", log.source ?: "Unknown")
        }
        if (options.verbose) {
            val map = log.metadata.toMutableMap()
            map.remove("source")
            putAll(map)
        }
    }.entries.joinToString(prefix = "{${options.tab}\n", separator = ",\n", postfix = "\n}") { (key, value) ->
        """${options.tab}"$key": "$value""""
    }
}