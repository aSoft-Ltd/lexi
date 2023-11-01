package lexi

data class Log(
    val level: LogLevel,
    val message: String,
    val status: LogStatus?,
    val source: String,
    val metadata: Map<String, Any?>
) 