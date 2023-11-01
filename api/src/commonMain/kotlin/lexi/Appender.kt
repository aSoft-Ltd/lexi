package lexi

interface Appender {
    fun append(log: Log)

    fun trace(msg: String, vararg data: Pair<String, Any?>): LogTracer

    fun debug(msg: String, vararg data: Pair<String, Any?>)

    fun info(msg: String, vararg data: Pair<String, Any?>)

    fun warn(msg: String, vararg data: Pair<String, Any?>)

    fun error(msg: String, c: Throwable?, vararg data: Pair<String, Any?>)

    fun error(msg: String, vararg data: Pair<String, Any?>)

    fun error(c: Throwable?)

    fun fatal(msg: String, c: Throwable?, vararg data: Pair<String, Any?>)

    fun fatal(msg: String, vararg data: Pair<String, Any?>)

    fun fatal(c: Throwable?)
}