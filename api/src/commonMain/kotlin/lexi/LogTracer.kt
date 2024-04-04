package lexi

class LogTracer(val message: String, private val appender: Appender) {
    fun update(message: String) {
        val log = Log(
            level = LogLevel.TRACE,
            message = this.message,
            status = LogStatus.Progressing,
            source = "Unset",
            metadata = mapOf("update" to message)
        )
        appender.append(log)
    }

    fun debug(message: String) = appender.debug(message)

    fun passed() {
        val log = Log(
            level = LogLevel.INFO,
            message = this.message,
            status = LogStatus.Passed,
            source = "Unset",
            metadata = mapOf()
        )
        appender.append(log)
    }

    inline fun <R> success(block: () -> R): R = try {
        val result = block()
        passed()
        result
    } catch (exp: Throwable) {
        error(exp)
    }

    fun failed(exp: Throwable?) {
        val log = Log(
            level = LogLevel.ERROR,
            message = this.message,
            status = LogStatus.Failed,
            source = "Unset",
            metadata = mapOf("cause" to exp?.message)
        )
        appender.append(log)
    }

    fun error(exp: Throwable): Nothing {
        val log = Log(
            level = LogLevel.ERROR,
            message = this.message,
            status = LogStatus.Failed,
            source = "Unset",
            metadata = mapOf("cause" to exp.message)
        )
        appender.append(log)
        throw exp
    }

    fun error(message: String = "Unknown error"): Nothing = error(RuntimeException(message))
}