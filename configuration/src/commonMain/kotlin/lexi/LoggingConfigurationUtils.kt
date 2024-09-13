package lexi

fun LoggingConfiguration?.build(): LoggerFactory {
    val factory = LoggerFactory()
    if (this == null) return factory
    solid.forEach { factory.add(it()) }
    appender.forEach {
        val type = it["type"] ?: IllegalArgumentException("Appender type is required")
        val builder = appenders[type] ?: error("Unknown appender type: $type")
        factory.add(builder(it.setDefaults(this)))
    }
    return factory
}

private fun Map<String, String>.setDefaults(config: LoggingConfiguration): Map<String, String> {
    val result = toMutableMap()
    result.getOrPut(config::level.name) { config.level ?: LogLevel.DEBUG.toString() }
    result.getOrPut(config::source.name) { config.source?.toString() ?: "true" }
    result.getOrPut(config::status.name) { config.status?.toString() ?: "true" }
    result.getOrPut(config::verbose.name) { config.verbose?.toString() ?: "true" }
    return result
}