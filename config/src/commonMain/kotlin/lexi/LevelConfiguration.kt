package lexi

enum class LevelConfiguration {
    debug, info, warning, error, failure;
    fun toLevel() = when(this) {
        debug -> LogLevel.DEBUG
        info -> LogLevel.INFO
        warning -> LogLevel.WARNING
        error -> LogLevel.ERROR
        failure -> LogLevel.FAILURE
    }
}