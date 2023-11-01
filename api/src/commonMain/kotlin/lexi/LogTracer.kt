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
}