package lexi

fun loggerFactory(name: String = "Unknown", builder: LoggerFactory.() -> Unit) = LoggerFactory(name).apply(builder)