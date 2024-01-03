package lexi

enum class LogLevel {
    DEBUG, TRACE, INFO, WARNING, ERROR, FATAL;

    companion object {
        fun parse(value: String?): LogLevel? {
            if (value == null) return null
            return LogLevel.entries.firstOrNull {
                it.name == value.uppercase()
            } ?: throw IllegalArgumentException("Unknown logging level: $this")
        }
    }
}